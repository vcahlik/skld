package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.ProductPosition;
import cz.cvut.fit.project.skld.application.db.PositionDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/***
 * An implementation of PositionDAO powered by Dropwizard's AbstractDAO implementation.
 */
public class PostgresPositionDAO extends AbstractDAO<ProductPosition>implements PositionDAO {
    public PostgresPositionDAO(SessionFactory factory) {
        super(factory);
    }

    @Override public List<ProductPosition> findForProductId(long productId) {
        Query<ProductPosition> q = query("SELECT new cz.cvut.fit.project.skld.application.core.ProductPosition(pm.location, SUM(pm.quantity)) FROM ProductMovement pm  WHERE pm.product.id = :id GROUP BY pm.location");
        q.setParameter("id", productId);
        return list(q);
    }
}
