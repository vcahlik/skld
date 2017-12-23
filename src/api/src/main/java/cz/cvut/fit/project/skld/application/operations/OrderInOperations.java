package cz.cvut.fit.project.skld.application.operations;

import cz.cvut.fit.project.skld.application.core.*;
import cz.cvut.fit.project.skld.application.db.MovementDAO;
import cz.cvut.fit.project.skld.application.db.OrderInDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.operations.exceptions.InvalidStateException;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundException;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundExceptionSupplier;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementuje operace souvisejici s logistickymi objednavkami.
 */
public class OrderInOperations {
    private final OrderInDAO orderInDAO;
    private final ProductDAO productDAO;
    private final MovementDAO movementDAO;

    /**
     * Konstruktor.
     * @param orderInDAO DAO pro pristup k logistickym objednavkam
     * @param productDAO DAO pro pristup k produktum
     * @param movementDAO DAO pro pristup k umistenim produktu
     */
    public OrderInOperations(OrderInDAO orderInDAO, ProductDAO productDAO, MovementDAO movementDAO) {
        this.orderInDAO = orderInDAO;
        this.productDAO = productDAO;
        this.movementDAO = movementDAO;
    }

    /**
     * Vraci logistickou objednavku podle zadaneho ID.
     * @param id ID
     */
    public OrderIn get(long id) throws NotFoundException {
        Optional<OrderIn> order = orderInDAO.findById(id);
        if (!order.isPresent()) {
            throw new NotFoundException();
        }
        return order.get();
    }

    /**
     * Upravi logistickou objednavku se zadanym ID.
     * V soucasnosti smi byt modifikovan pouze status.
     * @throws InvalidStateException Editovana objednavka neni ve stavu otevreno
     */
    public OrderIn edit(OrderInChange change) throws NotFoundException, InvalidStateException {
        OrderIn order = orderInDAO.findById(change.getId()).orElseThrow(new NotFoundExceptionSupplier());
        if (order.getState() != OrderState.OPEN) {
            throw new InvalidStateException("Only open orders can be edited.");
        }
        order.setSupplierName(change.getSupplierName());
        order.setExpectedDelivery(change.getDeliveryDate());
        Map<Long, LineItem> lineItemsByProduct = order.lineItemMap();
        for (ProductRepresentation prod : change.getProducts()) {
            if (lineItemsByProduct.containsKey(prod.getId())) {
                LineItem li = lineItemsByProduct.get(prod.getId());
                long quantity = prod.getQuantity();
                if (quantity == 0) {
                    order.getLineItems().remove(li);
                    li.setOrder(null);
                    orderInDAO.removeLineItem(li);
                } else {
                    li.setQuantity(prod.getQuantity());
                }
            } else {
                Product lineItemProduct = productDAO.findById(prod.getId()).orElseThrow(new NotFoundExceptionSupplier());
                order.getLineItems().add(new LineItem(prod.getQuantity(), lineItemProduct, order));
            }
        }
        return order;
    }

    /**
     * Uzavre logistickou objednavku a zada prislusna umisteni produktu do systemu podle predanych produktu z requestu
     * @param handler Uzivatel, ktery operaci zadal do systemu
     * @param request Uzavirana logisticka objednavka
     * @throws InvalidStateException Objednavka neni ve stavu otevreno
     */
    public OrderIn close(User handler, OrderInRepresentation request) throws NotFoundException, InvalidStateException {
        OrderIn order = orderInDAO.findById(request.getId()).orElseThrow(new NotFoundExceptionSupplier());
        if (order.getState() != OrderState.OPEN) {
            throw new InvalidStateException("Only open orders can be closed.");
        }
        Map<Long, LineItem> lineItems = order.lineItemMap();
        Set<Long> product_ids = request.getProducts()
                .stream()
                .map(ProductRepresentation::getId)
                .collect(Collectors.toSet());
        product_ids.removeAll(lineItems.keySet());
        if (!product_ids.isEmpty()) {
            throw new InvalidStateException("Products you are trying to allocate are not present in this order");
        }

        for (ProductRepresentation pr : request.getProducts()) {
            Product product = productDAO.findById(pr.getId()).orElseThrow(new NotFoundExceptionSupplier());
            Map<String, Long> positions = pr.getPositions();
            LineItem lineItem = lineItems.get(product.getId());
            if (sumMapKeys(positions) > lineItem.getQuantity()) {
                throw new InvalidStateException("You are trying to allocate more pieces of product than ordered");
            }
            for (String position : positions.keySet()) {
                ProductMovement pm = new ProductMovement(product, positions.get(position), position, handler);
                movementDAO.create(pm);
                lineItem.getProductAllocations().add(pm);
            }
        }
        order.setState(OrderState.CLOSED);
        order.setHandlingDetails(handler);
        return order;
    }

    /**
     * Zamitne logistickou objednavku.
     * @param handler Uzivatel, ktery operaci zadal do systemu
     * @param id ID zamitane logisticke objednavky
     * @throws InvalidStateException Objednavka neni ve stavu otevreno
     */
    public OrderIn refuse(User handler, long id) throws NotFoundException, InvalidStateException {
        OrderIn order = orderInDAO.findById(id).orElseThrow(new NotFoundExceptionSupplier());
        if (order.getState() != OrderState.OPEN) {
            throw new InvalidStateException("Only open orders can be refused.");
        }
        order.setState(OrderState.REFUSED);
        order.setHandlingDetails(handler);
        return order;
    }

    /**
     * Vytvori novou logistickou objednavku.
     * @param creator Uzivatel, ktery operaci zadal do systemu
     * @throws InvalidStateException Logisticka objednavka se zadanym ID jiz v systemu existuje
     */
    public OrderIn create(User creator, OrderInChange request) throws InvalidStateException {
        Optional<OrderIn> existing = orderInDAO.findById(request.getId());
        if (existing.isPresent()) {
            throw new InvalidStateException("Order with the given ID already exists");
        }
        OrderIn order = new OrderIn(request.getId(), creator, request.getSupplierName());
        order.setExpectedDelivery(request.getDeliveryDate());
        for (ProductRepresentation rep : request.getProducts()) {
            Product product = productDAO.findById(rep.getId()).orElseThrow(
                    new WebAppExceptionSupplier("Unknown product "+ Long.toString(rep.getId()), Response.Status.BAD_REQUEST));
            LineItem li = new LineItem(rep.getQuantity(), product, order);
            order.getLineItems().add(li);
        }
        orderInDAO.create(order);
        return order;
    }

    /**
     * Vraci seznam vsech logistickych objednavek z databaze.
     * @return Seznam logistickych objednavek
     */
    public List<OrderIn> getAll() {
        return orderInDAO.findAll();
    }

    private long sumMapKeys(Map<String, Long> m) {
        long sum = 0;
        for (String k : m.keySet()) {
            sum += m.get(k);
        }
        return sum;
    }
}
