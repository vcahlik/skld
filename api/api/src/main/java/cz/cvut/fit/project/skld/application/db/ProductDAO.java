package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.Product;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class ProductDAO extends AbstractDAO<Product> {
    public ProductDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Product create(Product product) {
        return persist(product);
    }

    public List<Product> findAll() {
        return list(namedQuery("Product.findAll"));
    }
}
