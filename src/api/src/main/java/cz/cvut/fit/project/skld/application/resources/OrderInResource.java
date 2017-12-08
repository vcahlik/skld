package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.application.core.OrderIn;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.operations.OrderInOperations;
import cz.cvut.fit.project.skld.application.operations.exceptions.InvalidStateException;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundException;
import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/orders/in/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderInResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final OrderInOperations orderInOps;

    public OrderInResource(OrderInOperations orderInOps) {
        this.orderInOps = orderInOps;
    }

    @GET
    @UnitOfWork
    public OrderInRepresentation getOrder(@PathParam("id") long id) {
        try {
            OrderIn order = orderInOps.get(id);
            return RepresentationConverter.representOrderIn(order);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Specified Order was not found", Response.Status.NOT_FOUND);
        }
    }

    @PUT
    @UnitOfWork
    public OrderInRepresentation updateOrder(@PathParam("id") long id, @Valid OrderInChange change) {
        if (id != change.getId()) {
            throw new WebApplicationException("Path and body IDs don't match.", Response.Status.BAD_REQUEST);
        }
        try {
            OrderIn order = orderInOps.edit(change);
            return RepresentationConverter.representOrderIn(order);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Specified Order or Product was not found.", Response.Status.NOT_FOUND);
        } catch (InvalidStateException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }


    @POST
    @UnitOfWork
    @Path("/close")
    public OrderInRepresentation closeOrder(@Auth User user, @PathParam("id") long id, OrderInRepresentation request) {
        request.setId(id);
        try {
            OrderIn order = orderInOps.close(user, request);
            return RepresentationConverter.representOrderIn(order);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Specified Order or Product was not found.", Response.Status.NOT_FOUND);
        } catch (InvalidStateException e) {
            throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
        }
    }

    @POST
    @UnitOfWork
    @Path("/refuse")
    public OrderInRepresentation refuseOrder(@Auth User user, @PathParam("id") long id) {
        try {
            return RepresentationConverter.representOrderIn(orderInOps.refuse(user, id));
        } catch (NotFoundException e) {
            throw new WebApplicationException("Specified Order was not found", Response.Status.NOT_FOUND);
        } catch (InvalidStateException e) {
            throw new WebApplicationException("You can only refuse open orders", Response.Status.BAD_REQUEST);
        }
    }
}
