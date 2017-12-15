package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductMovement;

import java.util.Optional;

/***
 * Implements basic database operations over ProductMovement objects.
 */
public interface MovementDAO {
    /***
     * Get a product movement with the given ID.
     * @param id the ID of the searched-for product movement.
     * @return optionally the product movement that's in the database under the given ID
     */
    Optional<ProductMovement> findById(Long id);

    /***
     * Insert a new product movement into the database.
     * @param movement the product movement to create
     * @return created product movement, with its generated fields filled-out.
     */
    ProductMovement create(ProductMovement movement);
}
