package cz.cvut.fit.project.skld.api.resources;

import cz.cvut.fit.project.skld.api.api.OrderInRepresentation;
import cz.cvut.fit.project.skld.api.api.ProductRepresentation;
import cz.cvut.fit.project.skld.api.core.LineItem;
import cz.cvut.fit.project.skld.api.core.OrderIn;
import cz.cvut.fit.project.skld.api.core.OrderState;
import cz.cvut.fit.project.skld.api.core.Product;
import cz.cvut.fit.project.skld.api.db.OrderInDAO;
import cz.cvut.fit.project.skld.api.db.ProductDAO;
import cz.cvut.fit.project.skld.api.util.WebAppExceptionSupplier;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/orders/in/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderInResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final OrderInDAO orderInDAO;
    private final ProductDAO productDAO;

    public OrderInResource(OrderInDAO orderInDAO, ProductDAO productDAO) {
        this.orderInDAO = orderInDAO;
        this.productDAO = productDAO;
    }

    @GET
    @UnitOfWork
    public OrderInRepresentation getOrder(@PathParam("id") long id) {
        OrderIn order = orderInDAO.findById(id).orElseThrow(new WebAppExceptionSupplier("Specified Order was not found", Response.Status.NOT_FOUND));
        return new OrderInRepresentation(order);
    }

    @PUT
    @UnitOfWork
    public OrderInRepresentation updateOrder(@PathParam("id") long id, OrderInRepresentation data) {
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
        return new OrderInRepresentation(order);
    }
}
