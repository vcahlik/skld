package cz.cvut.fit.project.skld.api.db;

import cz.cvut.fit.project.skld.api.core.ProductPosition;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PositionDAO extends AbstractDAO<ProductPosition> {
    public PositionDAO(SessionFactory factory) {
        super(factory);
    }

    public List<ProductPosition> findForProductId(long productId) {
        Query<ProductPosition> q = query("SELECT new cz.cvut.fit.project.skld.api.core.ProductPosition(pm.location, SUM(pm.quantity)) FROM ProductMovement pm  WHERE pm.product.id = :id GROUP BY pm.location");
        q.setParameter("id", productId);
        return list(q);
    }
}
