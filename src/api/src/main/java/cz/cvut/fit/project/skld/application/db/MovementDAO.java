package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductMovement;

import java.util.Optional;

public interface MovementDAO {
    Optional<ProductMovement> findById(Long id);

    ProductMovement create(ProductMovement movement);
}
