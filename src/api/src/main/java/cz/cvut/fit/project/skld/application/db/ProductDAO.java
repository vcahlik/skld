package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.Product;

import java.util.List;
import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad Product objekty.
 */
public interface ProductDAO {
    /**
     * Vraci produkt se zadanym ID.
     * @param id ID produktu
     * @return Produkt
     */
    Optional<Product> findById(Long id);

    /**
     * Vlozi novy produkt do databaze.
     * @param product Vkladany produkt
     * @return Vytvoreny produkt (vcetne pripadnych automaticky generovanych hodnot)
     */
    Product create(Product product);

    /**
     * Vraci vsechny produkty v databazi.
     * @return Seznam produktu
     */
    List<Product> findAll();
}
