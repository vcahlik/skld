package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class OrderInDAO extends AbstractDAO<OrderIn> {
    public OrderInDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<OrderIn> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public OrderIn create(OrderIn order) {
        return persist(order);
    }

    public List<OrderIn> findAll() {
        return list(namedQuery("OrderIn.findAll"));
    }

    public void removeLineItem(LineItem li) {
        currentSession().delete(li);
    }
}
