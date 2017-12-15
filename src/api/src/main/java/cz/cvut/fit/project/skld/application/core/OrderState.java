package cz.cvut.fit.project.skld.application.core;


/***
 * Represents the state of an order.
 */
public enum OrderState {
    /***
     * An open order has been received from the operations
     * department and is waiting for processing in the warehouse.
     */
    OPEN,
    /**
     * The order has been processed.
     */
    CLOSED,
    /***
     * There was a problem when processing the order so it is cancelled.
     */
    REFUSED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
