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

/**
 * Implementuje REST koncove body aplikace, ktere umoznuji uzivatelum tvorit a vyhledavat produkty.
 */
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductsResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final ProductOperations productOps;

    /**
     * Konstruktor.
     * @param ops ProductOperations s business logikou produktu
     */
    public ProductsResource(ProductOperations ops) {
        productOps = ops;
    }

    /**
     * Prida do systemu zadany produkt.
     * @param user Uzivatel, ktery operaci provedl
     * @param product Pridavany produkt
     * @return Pridany produkt (tak, jak vypada v systemu)
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

    /**
     * Vrati seznam vsech produktu v systemu.
     * @return Seznam produktu
     */
    @GET
    @UnitOfWork
    public List<ProductRepresentation> listProducts() {
        return productOps.list().stream().map(RepresentationConverter::representProduct).collect(Collectors.toList());
    }
}
