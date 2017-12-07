package cz.cvut.fit.project.skld.application.resources;


import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;
import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.OrderInDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/orders/in")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderInsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final OrderInDAO orderInDAO;
    private final ProductDAO productDAO;

    public OrderInsResource(OrderInDAO orderInDAO, ProductDAO productDAO) {
        this.orderInDAO = orderInDAO;
        this.productDAO = productDAO;
    }

    @POST
    @UnitOfWork
    @RolesAllowed({"admin"})
    public OrderInRepresentation create(@Auth User user, @Valid OrderInChange request) {
        OrderIn order = new OrderIn(request.getId(), user, request.getSupplierName());
        order.setExpectedDelivery(request.getDeliveryDate());
        for (ProductRepresentation rep : request.getProducts()) {
            Product product = productDAO.findById(rep.getId()).orElseThrow(
                    new WebAppExceptionSupplier("Unknown product "+ Long.toString(rep.getId()), Response.Status.BAD_REQUEST));
            LineItem li = new LineItem(rep.getQuantity(), product, order);
            order.getLineItems().add(li);
        }
        orderInDAO.create(order);
        return RepresentationConverter.representOrderIn(order);
    }

    @GET
    @UnitOfWork
    public List<OrderInRepresentation> getAll() {
        List<OrderIn> orders = orderInDAO.findAll();

        return orders.stream().map(RepresentationConverter::representOrderIn).collect(Collectors.toList());
    }
}
