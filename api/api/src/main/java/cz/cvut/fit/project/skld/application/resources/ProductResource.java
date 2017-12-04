package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.api.ProductEdit;
import cz.cvut.fit.project.skld.api.ProductRepresentation;
import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.db.PositionDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private final WebAppExceptionSupplier error404 = new WebAppExceptionSupplier("Product not found", Response.Status.NOT_FOUND);
    final ProductDAO productDAO;
    final PositionDAO positionDAO;

    public ProductResource(ProductDAO productDAO, PositionDAO positionDAO) {
        this.productDAO = productDAO;
        this.positionDAO = positionDAO;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRepresentation get(@NotNull @PathParam("id") long productId) {
        return generateRepresentation(productDAO.findById(productId).orElseThrow(error404));
    }

    @PUT
    @UnitOfWork
    @RolesAllowed({"admin"})
    public ProductRepresentation edit(@NotNull @PathParam("id") long productId, ProductEdit changes) {
        Product p = productDAO.findById(productId).orElseThrow(error404);
        p.setName(changes.getName());
        return generateRepresentation(p);
    }

    private ProductRepresentation generateRepresentation(Product p) {
        return new ProductRepresentation(p.getId(), p.getName(), positionDAO.findForProductId(p.getId()));
    }
}
