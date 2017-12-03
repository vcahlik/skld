package cz.cvut.fit.project.skld.api.db;

import cz.cvut.fit.project.skld.api.core.ProductMovement;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class MovementDAO extends AbstractDAO<ProductMovement> {
    public MovementDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<ProductMovement> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public ProductMovement create(ProductMovement movement) {
        return persist(movement);
    }

}

