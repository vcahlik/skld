package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.ProductPosition;
import cz.cvut.fit.project.skld.application.db.PositionDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Implementuje zjistovani pozic produktu.
 * Tato trida neobsahuje metody pro tvoreni novych pozic nebo pokrocilych queries jako v ostatnich tridach v tomto balicku, protoze ProductPositions nejsou reprezentovany na DB urovni.
 */
public class PostgresPositionDAO extends AbstractDAO<ProductPosition>implements PositionDAO {
    /**
     * Konstruktor.
     * @param factory Factory specifikovana v Dropwizard frameworku
     */
    public PostgresPositionDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Vraci pozice produktu se zadanym ID.
     * @param productId ID produktu
     * @return Seznam pozic, na kterych je produkt umisten
     */
    @Override public List<ProductPosition> findForProductId(long productId) {
        Query<ProductPosition> q = query("SELECT new cz.cvut.fit.project.skld.application.core.ProductPosition(pm.location, SUM(pm.quantity)) FROM ProductMovement pm  WHERE pm.product.id = :id GROUP BY pm.location");
        q.setParameter("id", productId);
        return list(q);
    }
}
