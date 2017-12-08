package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.operations.ProductOperations;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
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

    private final ProductOperations productOps;

    public ProductsResource(ProductOperations ops) {
        productOps = ops;
    }

    @POST
    @UnitOfWork
    @RolesAllowed({"admin"})
    public ProductRepresentation createProduct(@Auth User user, @Valid ProductChange product) {
        return RepresentationConverter.representProduct(productOps.create(user, product));
    }

    @GET
    @UnitOfWork
    public List<ProductRepresentation> listProducts() {
        return productOps.list().stream().map(RepresentationConverter::representProduct).collect(Collectors.toList());
    }
}
