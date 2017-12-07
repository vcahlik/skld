package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.project.skld.application.db.MovementDAO;
import cz.cvut.fit.project.skld.application.db.OrderInDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;
import cz.cvut.fit.project.skld.application.core.OrderState;
import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.core.ProductMovement;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/orders/in/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderInResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final OrderInDAO orderInDAO;
    private final ProductDAO productDAO;
    private final MovementDAO movementDAO;

    public OrderInResource(OrderInDAO orderInDAO, ProductDAO productDAO, MovementDAO movementDAO) {
        this.orderInDAO = orderInDAO;
        this.productDAO = productDAO;
        this.movementDAO = movementDAO;
    }

    @GET
    @UnitOfWork
    public OrderInRepresentation getOrder(@PathParam("id") long id) {
        OrderIn order = orderInDAO.findById(id).orElseThrow(new WebAppExceptionSupplier("Specified Order was not found", Response.Status.NOT_FOUND));
        return RepresentationConverter.representOrderIn(order);
    }

    @PUT
    @UnitOfWork
    public OrderInRepresentation updateOrder(@PathParam("id") long id, @Valid OrderInChange data) {
        if (id != data.getId()) {
            throw new WebApplicationException("Path and body IDs don't match.", Response.Status.BAD_REQUEST);
        }
        OrderIn order = orderInDAO.findById(id).orElseThrow(new WebAppExceptionSupplier("Specified Order was not found", Response.Status.NOT_FOUND));
        if (order.getState() != OrderState.OPEN) {
            throw new WebApplicationException("Closed and refused orders cannot be edited", Response.Status.BAD_REQUEST);
        }
        order.setSupplierName(data.getSupplierName());
        order.setExpectedDelivery(data.getDeliveryDate());
        Map<Long, LineItem> lineItemsByProduct = order.lineItemMap();
        for (ProductRepresentation prod : data.getProducts()) {
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
                Product lineItemProduct = productDAO.findById(prod.getId())
                        .orElseThrow(new WebAppExceptionSupplier("The order is referring to a product that doesn't exist", Response.Status.NOT_FOUND));
                order.getLineItems().add(new LineItem(prod.getQuantity(), lineItemProduct, order));
            }
        }
        return RepresentationConverter.representOrderIn(order);
    }


    @POST
    @UnitOfWork
    @Path("/close")
    public OrderInRepresentation closeOrder(@Auth User user, @PathParam("id") long id, OrderInRepresentation request) {
        OrderIn order = orderInDAO.findById(id).orElseThrow(
                new WebAppExceptionSupplier("Specified Order was not found", Response.Status.NOT_FOUND));
        if (order.getState() != OrderState.OPEN) {
            throw new WebApplicationException("You can only close open orders", Response.Status.BAD_REQUEST);
        }
        Map<Long, LineItem> lineItems = order.lineItemMap();
        Set<Long> product_ids = request.getProducts()
                .stream()
                .map((ProductRepresentation pr) -> pr.getId())
                .collect(Collectors.toSet());
        product_ids.removeAll(lineItems.keySet());
        if (!product_ids.isEmpty()) {
            throw new WebApplicationException("Products you are trying to allocate are not present in this order",
                    Response.Status.BAD_REQUEST);
        }

        for (ProductRepresentation pr : request.getProducts()) {
            Product product = productDAO.findById(pr.getId()).orElseThrow(
                    new WebAppExceptionSupplier("Couldn't find product you are allocating", Response.Status.NOT_FOUND));
            Map<String, Long> positions = pr.getPositions();
            LineItem lineItem = lineItems.get(product.getId());
            if (sumMapKeys(positions) > lineItem.getQuantity()) {
                throw new WebApplicationException("You are trying to allocate more pieces of product than ordered", Response.Status.BAD_REQUEST);
            }
            for (String position : positions.keySet()) {
                ProductMovement pm = new ProductMovement(product, positions.get(position), position, user);
                movementDAO.create(pm);
                lineItem.getProductAllocations().add(pm);
            }
        }
        order.setState(OrderState.CLOSED);
        order.setHandlingDetails(user);
        return RepresentationConverter.representOrderIn(order);
    }

    @POST
    @UnitOfWork
    @Path("/refuse")
    public OrderInRepresentation refuseOrder(@Auth User user, @PathParam("id") long id) {
        OrderIn order = orderInDAO.findById(id).orElseThrow(new WebAppExceptionSupplier("Specified Order was not found", Response.Status.NOT_FOUND));
        if (order.getState() != OrderState.OPEN) {
            throw new WebApplicationException("You can only refuse open orders", Response.Status.BAD_REQUEST);
        }
        order.setState(OrderState.REFUSED);
        order.setHandlingDetails(user);
        return RepresentationConverter.representOrderIn(order);
    }

    private long sumMapKeys(Map<String, Long> m) {
        long sum = 0;
        for (String k : m.keySet()) {
            sum += m.get(k);
        }
        return sum;
    }
}
