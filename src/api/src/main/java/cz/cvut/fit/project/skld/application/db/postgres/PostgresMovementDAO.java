package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.ProductMovement;
import cz.cvut.fit.project.skld.application.db.MovementDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

/***
 * An implementation of MovementDAO powered by Dropwizard's AbstractDAO implementation.
 */
public class PostgresMovementDAO extends AbstractDAO<ProductMovement>implements MovementDAO {
    public PostgresMovementDAO(SessionFactory factory) {
        super(factory);
    }

    @Override public Optional<ProductMovement> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Override public ProductMovement create(ProductMovement movement) {
        return persist(movement);
    }

}

