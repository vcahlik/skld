package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/***
 * An implementation of ProductDAO powered by Dropwizard's AbstractDAO implementation.
 */
public class PostgresProductDAO extends AbstractDAO<Product>implements ProductDAO {
    public PostgresProductDAO(SessionFactory factory) {
        super(factory);
    }

    @Override public Optional<Product> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Override public Product create(Product product) {
        return persist(product);
    }

    @Override public List<Product> findAll() {
        return list(namedQuery("Product.findAll"));
    }
}
