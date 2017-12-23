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

/**
 * Implementuje operace souvisejici s produkty.
 */
public class ProductOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductOperations.class);

    private final ProductDAO productDAO;
    private final PositionDAO positionDAO;

    /**
     * Konstruktor.
     * @param pDao DAO pro pristup k produktum
     * @param posDao DAO pro pristup k pozicim
     */
    public ProductOperations(ProductDAO pDao, PositionDAO posDao) {
        productDAO = pDao;
        this.positionDAO = posDao;
    }

    /**
     * Vytvori novy produkt.
     * @param creator Uzivatel, ktery operaci zadal do systemu
     * @param product Objekt obsahujici informace, podle kterych bude produkt vytvoren
     * @return Vytvoreny produkt (vcetne pripadnych automaticky generovanych hodnot)
     * @throws InvalidStateException Produkt se stejnym ID jiz v databazi existuje
     */
    public Product create(User creator, ProductChange product) throws InvalidStateException {
        Optional<Product> existing = productDAO.findById(product.getId());
        if (existing.isPresent()) {
            throw new InvalidStateException("Product with the given ID already exists.");
        }
        Product p = new Product(product.getId(), product.getName(), creator);
        return productDAO.create(p);
    }

    /**
     * Get a list of all the products that are in the database
     * @return a list of all the products that are in the database
     */
    public List<Product> list() {
        return productDAO.findAll();
    }

    /**
     * Vraci produkt se zadanym ID.
     * @param id ID produktu
     * @return Pozadovany produkt
     * @throws NotFoundException Produkt se zadanym ID v systemu neexistuje
     */
    public Product get(long id) throws NotFoundException {
        return productDAO.findById(id).orElseThrow(new NotFoundExceptionSupplier());
    }

    /**
     * Zmeni nazev produktu se zadanym ID.
     * @param change Objekt obsahujici informace, podle kterych bude produkt zmenen
     * @throws NotFoundException Produkt se zadanym ID v systemu neexistuje
     */
    public Product edit(ProductChange change) throws NotFoundException {
        Product p = productDAO.findById(change.getId()).orElseThrow(new NotFoundExceptionSupplier());
        p.setName(change.getName());
        return p;
    }

    /**
     * Vraci seznam pozic, na kterych je produkt ve skladu umisten.
     * @param id ID produktu
     * @returna Seznam pozic
     */
    public List<ProductPosition> positionsForProduct(long id) {
        return positionDAO.findForProductId(id);
    }
}
