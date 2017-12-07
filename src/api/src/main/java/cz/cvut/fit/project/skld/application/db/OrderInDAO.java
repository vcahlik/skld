package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;

import java.util.List;
import java.util.Optional;

public interface OrderInDAO {
    Optional<OrderIn> findById(Long id);

    OrderIn create(OrderIn order);

    List<OrderIn> findAll();

    void removeLineItem(LineItem li);
}
