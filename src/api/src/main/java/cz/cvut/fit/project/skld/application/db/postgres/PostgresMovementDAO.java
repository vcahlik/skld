package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.ProductMovement;
import cz.cvut.fit.project.skld.application.db.MovementDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad MovementDAO objekty.
 */
public class PostgresMovementDAO extends AbstractDAO<ProductMovement>implements MovementDAO {
    /**
     * Konstruktor.
     * @param factory Factory specifikovana v Dropwizard frameworku
     */
    public PostgresMovementDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Vraci umisteni produktu (ProductMovement) se zadanym ID.
     * @param id ID
     * @return ProductMovement se zadanym ID
     */
    @Override public Optional<ProductMovement> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Vlozi do databaze nove umisteni produktu (ProductMovement).
     * @param movement ProductMovement
     * @return Vytvoreny ProductMovement (vcetne pripadnych automaticky generovanych hodnot)
     */
    @Override public ProductMovement create(ProductMovement movement) {
        return persist(movement);
    }

}

