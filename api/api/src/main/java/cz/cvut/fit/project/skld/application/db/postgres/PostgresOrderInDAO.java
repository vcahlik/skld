package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;
import cz.cvut.fit.project.skld.application.db.OrderInDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PostgresOrderInDAO extends AbstractDAO<OrderIn> implements OrderInDAO {
    public PostgresOrderInDAO(SessionFactory factory) {
        super(factory);
    }

    @Override public Optional<OrderIn> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Override public OrderIn create(OrderIn order) {
        return persist(order);
    }

    @Override public List<OrderIn> findAll() {
        return list(namedQuery("OrderIn.findAll"));
    }

    @Override public void removeLineItem(LineItem li) {
        currentSession().delete(li);
    }
}
