package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductPosition;

import java.util.List;

/***
 * Implements getting ProductPosition lists for products.
 * Since ProductPosition is not represented on the database level, this class
 * doesn't have any of the creation and advanced querying methods from other DAOs.
 */
public interface PositionDAO {
    /***
     * Get ProductPositions for the product with the given ID.
     * @param productId ID of the product to get positions for
     * @return a list of ProductPositions where the product is currently located.
     */
    List<ProductPosition> findForProductId(long productId);
}
