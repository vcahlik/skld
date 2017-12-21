package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.operations.ProductOperations;
import cz.cvut.fit.project.skld.application.operations.exceptions.InvalidStateException;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
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

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final ProductOperations productOps;

    /***
     * Construct a new ProductResource
     * @param ops business logic class implementing operations on Products
     */
    public ProductsResource(ProductOperations ops) {
        productOps = ops;
    }

    /***
     * Create a new product.
     */
    @POST
    @UnitOfWork
    @RolesAllowed({"admin"})
    public ProductRepresentation createProduct(@Auth User user, @Valid ProductChange product) {
        try {
            return RepresentationConverter.representProduct(productOps.create(user, product));
        } catch (InvalidStateException e) {
            throw new WebApplicationException("Product with the given ID already exists", Response.Status.BAD_REQUEST);
        }
    }

    /***
     * List all the Products
     */
    @GET
    @UnitOfWork
    public List<ProductRepresentation> listProducts() {
        return productOps.list().stream().map(RepresentationConverter::representProduct).collect(Collectors.toList());
    }
}
