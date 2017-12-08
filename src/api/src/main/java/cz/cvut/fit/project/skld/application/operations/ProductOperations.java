package cz.cvut.fit.project.skld.application.operations;

import cz.cvut.fit.project.skld.application.core.Product;
import cz.cvut.fit.project.skld.application.core.ProductPosition;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.PositionDAO;
import cz.cvut.fit.project.skld.application.db.ProductDAO;
import cz.cvut.fit.project.skld.application.operations.exceptions.NotFoundException;
import cz.cvut.fit.project.skld.representations.ProductChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class ProductOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOperations.class);

    private final ProductDAO productDAO;
    private final PositionDAO positionDAO;


    public ProductOperations(ProductDAO pDao, PositionDAO posDao) {
        productDAO = pDao;
        this.positionDAO = posDao;
    }

    public Product create(User creator, ProductChange product) {
        Product p = new Product(product.getId(), product.getName(), creator);
        return productDAO.create(p);
    }

    public List<Product> list() {
        return productDAO.findAll();
    }

    public Product get(long id) throws NotFoundException {
        Optional<Product> p = productDAO.findById(id);
        if (!p.isPresent()) {
            throw new NotFoundException();
        }
        return p.get();
    }

    public Product edit(ProductChange change) throws NotFoundException {
        Optional<Product> p = productDAO.findById(change.getId());
        if (!p.isPresent()) {
            throw new NotFoundException();
        }
        p.get().setName(change.getName());
        return p.get();
    }

    public List<ProductPosition> positionsForProduct(long id) {
        return positionDAO.findForProductId(id);
    }
}
