package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Optional<Product> findById(Long id);

    Product create(Product product);

    List<Product> findAll();
}
