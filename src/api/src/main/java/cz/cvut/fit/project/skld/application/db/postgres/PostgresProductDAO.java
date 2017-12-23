package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad Product objekty.
 */
public class PostgresProductDAO extends AbstractDAO<Product>implements ProductDAO {
    /**
     * Konstruktor.
     * @param factory Factory specifikovana v Dropwizard frameworku
     */
    public PostgresProductDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Vraci produkt se zadanym ID.
     * @param id ID produktu
     * @return Produkt
     */
    @Override public Optional<Product> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Vlozi novy produkt do databaze.
     * @param product Vkladany produkt
     * @return Vytvoreny produkt (vcetne pripadnych automaticky generovanych hodnot)
     */
    @Override public Product create(Product product) {
        return persist(product);
    }

    /**
     * Vraci vsechny produkty v databazi.
     * @return Seznam produktu
     */
    @Override public List<Product> findAll() {
        return list(namedQuery("Product.findAll"));
    }
}
