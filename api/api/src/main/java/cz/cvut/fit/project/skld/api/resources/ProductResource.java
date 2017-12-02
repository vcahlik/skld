package cz.cvut.fit.project.skld.api.resources;

import cz.cvut.fit.project.skld.api.api.ProductEdit;
import cz.cvut.fit.project.skld.api.core.Product;
import cz.cvut.fit.project.skld.api.db.ProductDAO;
import cz.cvut.fit.project.skld.api.util.NotFoundSupplier;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/products/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
    private final NotFoundSupplier error404 = new NotFoundSupplier("product");
    final ProductDAO productDAO;

    public ProductResource(ProductDAO dao) {
        this.productDAO = dao;
    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Product get(@NotNull @PathParam("id") long productId) {
        return productDAO.findById(productId).orElseThrow(error404);
    }

    @PUT
    @UnitOfWork
    @RolesAllowed({"admin"})
    public Product edit(@NotNull @PathParam("id") long productId, ProductEdit changes) {
        Product p = productDAO.findById(productId).orElseThrow(error404);
        p.setName(changes.getName());
        return p;
    }
}
