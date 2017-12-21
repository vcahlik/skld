package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.operations.ProductOperations;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundException;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/***
 * Implements REST endpoints which enable retrieval and manipulation of Product objects.
 */
@Path("/products/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private final WebAppExceptionSupplier error404 = new WebAppExceptionSupplier("Product not found", Response.Status.NOT_FOUND);
    final ProductOperations productOps;

    /***
     * Construct a new ProductResource
     * @param productOps business logic class implementing operations on Products
     */
    public ProductResource(ProductOperations productOps) {
        this.productOps = productOps;
    }

    /***
     * Get details of a product with the given ID
     */
    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRepresentation get(@NotNull @PathParam("id") long productId) {
        try {
            return generateRepresentation(productOps.get(productId));
        } catch (NotFoundException e) {
            throw new WebApplicationException("Couldn't find the requested product.", Response.Status.NOT_FOUND);
        }
    }

    /***
     * Edit the information about a Product
     */
    @PUT
    @UnitOfWork
    @RolesAllowed({"admin"})
    public ProductRepresentation edit(@NotNull @PathParam("id") long productId, @Valid ProductChange change) {
        if (productId != change.getId()) {
            throw new WebApplicationException("Path and body IDs don't match.", Response.Status.BAD_REQUEST);
        }
        Product p;
        try {
            p = productOps.edit(change);
        } catch (NotFoundException e) {
            throw new WebApplicationException("Couldn't find the requested product.", Response.Status.NOT_FOUND);
        }
        return generateRepresentation(p);
    }

    private ProductRepresentation generateRepresentation(Product p) {
        return RepresentationConverter.representProduct(p, productOps.positionsForProduct(p.getId()));
    }
}
