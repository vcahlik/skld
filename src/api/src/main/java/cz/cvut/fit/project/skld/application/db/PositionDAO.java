package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductPosition;

import java.util.List;

public interface PositionDAO {
    List<ProductPosition> findForProductId(long productId);
}
