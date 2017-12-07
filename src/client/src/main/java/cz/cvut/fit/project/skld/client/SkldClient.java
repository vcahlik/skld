package cz.cvut.fit.project.skld.client;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.representations.*;

import java.io.IOException;
import java.util.List;

/**
 * An interface for a client that can access the REST API of the SKLD information system.
 *
 * Please note that this interface doesn't provide a log in method. The client is supposed to be created with a
 * logged-in user which can provide the necessary tokens to call the API.
 *
 * Due to time contraints, the representation objects are used to cover disparate use cases. To know precisely
 * which fields should be filled in when calling an endpoint, please refer to the API documentation at:
 * https://skldapi.docs.apiary.io
 */
public interface SkldClient {

    /**
     * Returns the user that's currently logged in and which will be performing the
     * operations that this interface facilitates. This cannot return null since there
     * are no operations with anonymous access in the API.
     *
     * @return the currently logged in user.
     */
    UserRepresentation getLoggedInUser();

    /**
     * Return a list of all the orders that have arrived or will arrive into the warehouse.
     *
     * @return incoming orders that the information system knows about.
     */
    List<OrderInRepresentation> getOrderIns() throws IOException, APIException;

    /**
     * Create a new order in in the system according to the filled-in fields of the OrderInRepresentation (id, supplierName
     * and id and quantity for each product that will be delivered). This method can only be called by a user with admin
     * privileges.
     *
     * @return the created order, with the generated fields filled.
     */
    OrderInRepresentation createOrderIn(OrderInChange order) throws IOException, APIException;

    /**
     * Get detailed information about an order in.
     *
     * @param id the ID of the order in user wants to get information about.
     * @return the order in that was requested.
     */
    OrderInRepresentation getOrderIn(long id) throws IOException, APIException;

    /**
     * Change the metadata or ordered products of the given order.
     *
     * @param order a full object describing the order that's to be changed. Please note that not just changed fields
     *              but the whole object must be passed.
     * @return the updated order, as seen on the server side.
     */
    OrderInRepresentation updateOrderIn(OrderInChange order) throws IOException, APIException;

    /**
     * Set the order's status to closed and update product warehouse stocks according to the submitted product allocations.
     * In this case, only the products field of OrderInRepresentation needs to be filled in.
     * @param order an object containing the positions and quantities of products that have arrived.
     * @return the closed order, as seen on the server side.
     */
    OrderInRepresentation closeOrder(OrderInRepresentation order) throws IOException, APIException;

    /**
     * Set the status of the order with the given ID to refused.
     * @param id the ID of the order that should have its state set to refused
     * @return the refused order, as seen on the server side.
     */
    OrderInRepresentation refuseOrder(long id) throws IOException, APIException;

    /**
     * Return a list of all the products that are saved in the system.
     *
     * @return products that have been saved in the system.
     */
    List<ProductRepresentation> getProducts() throws IOException, APIException;

    /**
     * Create a new product in in the system according to the filled-in fields.
     *
     * @return the product that has been created.
     */
    ProductRepresentation createProduct(ProductChange product) throws IOException, APIException;

    /**
     * Return detailed information about a product.
     *
     * @param id ID od the product that should have its information returned
     * @return the product that has been requested
     */
    ProductRepresentation getProduct(long id) throws IOException, APIException;

    /**
     * Change product information. Currently, only product name can be changed.
     * @param edit an object containing product's new name and its ID.
     * @return the changed product, as seen on the server side.
     */
    ProductRepresentation changeProduct(ProductChange edit) throws IOException, APIException;
}
