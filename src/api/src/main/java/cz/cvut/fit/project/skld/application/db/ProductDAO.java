package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.Product;

import java.util.List;
import java.util.Optional;

/***
 * Implements basic database operations over Product objects.
 */
public interface ProductDAO {
    /***
     * Get a product with the given ID.
     * @param id the ID of the searched-for product.
     * @return optionally the product that's in the database under the given ID
     */
    Optional<Product> findById(Long id);

    /***
     * Insert a new product into the database.
     * @param product the product to create
     * @return created product, with its generated fields filled-out.
     */
    Product create(Product product);

    /***
     * Get all the products that are in the database.
     * @return all the products that are in the database.
     */
    List<Product> findAll();
}
