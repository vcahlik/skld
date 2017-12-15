package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;

import java.util.List;
import java.util.Optional;

/***
 * Implements basic database operations over OrderIn objects.
 */
public interface OrderInDAO {
    /***
     * Get an order in with the given ID.
     * @param id the ID of the searched-for order in.
     * @return optionally, the order in that's in the database under the given ID
     */
    Optional<OrderIn> findById(Long id);

    /***
     * Insert a new order in into the database.
     * @param order the order in to create
     * @return created order in, with its generated fields filled-out.
     */
    OrderIn create(OrderIn order);

    /***
     * Get all the order ins that are in the database.
     * @return all the order ins that are in the database.
     */
    List<OrderIn> findAll();

    /***
     * Remove a line item from the database.
     *
     * This method is located in the OrderInDAO class because line items are completely dependent on their orders
     * @param li the line item to remove.
     */
    void removeLineItem(LineItem li);
}
