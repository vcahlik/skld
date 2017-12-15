package cz.cvut.fit.project.skld.application.operations;

import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.core.ProductPosition;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.PositionDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.operations.exceptions.InvalidStateException;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundException;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundExceptionSupplier;
import cz.cvut.fit.project.skld.representations.ProductChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/***
 * Implements operations on products
 */
public class ProductOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOperations.class);

    private final ProductDAO productDAO;
    private final PositionDAO positionDAO;

    /***
     * Construct an instance of the class.
     * @param pDao DAO used to access product tables
     * @param posDao DAO used to access product position tables
     */
    public ProductOperations(ProductDAO pDao, PositionDAO posDao) {
        productDAO = pDao;
        this.positionDAO = posDao;
    }

    /***
     * Create a new product
     * @param creator the User that's creating the product and will be set as its creator
     * @param product the information that should be saved with the product
     * @return the created product, with its generated fields filled-out
     * @throws InvalidStateException A product with the given ID already exists in the database.
     */
    public Product create(User creator, ProductChange product) throws InvalidStateException {
        Optional<Product> existing = productDAO.findById(product.getId());
        if (existing.isPresent()) {
            throw new InvalidStateException("Product with the given ID already exists.");
        }
        Product p = new Product(product.getId(), product.getName(), creator);
        return productDAO.create(p);
    }

    /***
     * Get a list of all the products that are in the database
     * @return a list of all the products that are in the database
     */
    public List<Product> list() {
        return productDAO.findAll();
    }

    /***
     * Get details of a product with the given ID
     * @param id the ID of the product
     * @return the requested product
     * @throws NotFoundException there is no product with the given ID in the database
     */
    public Product get(long id) throws NotFoundException {
        return productDAO.findById(id).orElseThrow(new NotFoundExceptionSupplier());
    }

    /***
     * Change the name of the product with the given ID.
     * @param change an object representing the change request
     * @return complete information about the edited product
     * @throws NotFoundException there is no product with the given ID in the database
     */
    public Product edit(ProductChange change) throws NotFoundException {
        Product p = productDAO.findById(change.getId()).orElseThrow(new NotFoundExceptionSupplier());
        p.setName(change.getName());
        return p;
    }

    /***
     * Get a list of positions where the product is currently located in the warehouse
     * @param id the ID of the product
     * @returna list of positions where the product is currently located in the warehouse
     */
    public List<ProductPosition> positionsForProduct(long id) {
        return positionDAO.findForProductId(id);
    }
}
