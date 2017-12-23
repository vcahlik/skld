package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductMovement;

import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad MovementDAO objekty.
 */
public interface MovementDAO {
    /**
     * Vraci umisteni produktu (ProductMovement) se zadanym ID.
     * @param id ID
     * @return ProductMovement se zadanym ID
     */
    Optional<ProductMovement> findById(Long id);

    /**
     * Vlozi do databaze nove umisteni produktu (ProductMovement).
     * @param movement ProductMovement
     * @return Vytvoreny ProductMovement (vcetne pripadnych automaticky generovanych hodnot)
     */
    ProductMovement create(ProductMovement movement);
}
