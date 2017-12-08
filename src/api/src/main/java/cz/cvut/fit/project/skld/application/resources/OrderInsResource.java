package cz.cvut.fit.project.skld.application.resources;


import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.operations.OrderInOperations;
import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/orders/in")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderInsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final OrderInOperations orderInOps;

    public OrderInsResource(OrderInOperations orderInOps) {
        this.orderInOps = orderInOps;
    }

    @POST
    @UnitOfWork
    @RolesAllowed({"admin"})
    public OrderInRepresentation create(@Auth User user, @Valid OrderInChange request) {
        return RepresentationConverter.representOrderIn(orderInOps.create(user, request));
    }

    @GET
    @UnitOfWork
    public List<OrderInRepresentation> getAll() {
        return orderInOps.getAll().stream().map(RepresentationConverter::representOrderIn).collect(Collectors.toList());
    }
}
