package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.api.ProductRepresentation;
import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final ProductDAO productsDAO;

    public ProductsResource(ProductDAO pDao) {
        productsDAO = pDao;
    }

    @POST
    @UnitOfWork
    @RolesAllowed({"admin"})
    public ProductRepresentation createProduct(@Auth User user, Product product) {
        product.setCreator(user);
        LOGGER.debug("{} {} {}", product.getName(), product.getId(), product.getCreator());
        productsDAO.create(product);
        return RepresentationConverter.representProduct(product);
    }

    @GET
    @UnitOfWork
    public List<ProductRepresentation> listProducts() {
        return productsDAO.findAll().stream().map(RepresentationConverter::representProduct).collect(Collectors.toList());
    }
}
