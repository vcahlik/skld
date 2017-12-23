package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;
import cz.cvut.fit.project.skld.application.db.OrderInDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad OrderIn objekty.
 */
public class PostgresOrderInDAO extends AbstractDAO<OrderIn> implements OrderInDAO {
    /**
     * Konstruktor.
     * @param factory Factory specifikovana v Dropwizard frameworku
     */
    public PostgresOrderInDAO(SessionFactory factory) {
        super(factory);
    }

    /**
     * Vraci logistickou objednavku se zadanym ID.
     * @param id ID
     * @return Logisticka objednavka
     */
    @Override public Optional<OrderIn> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    /**
     * Vlozi do databaze novou logistickou objednavku.
     * @param order Logisticka objednavka
     * @return Vytvorena logisticka objednavka (vcetne pripadnych automaticky generovanych hodnot)
     */
    @Override public OrderIn create(OrderIn order) {
        return persist(order);
    }

    /**
     * Vraci seznam vsech logistickych objednavek.
     * @return Seznam objednavek
     */
    @Override public List<OrderIn> findAll() {
        return list(namedQuery("OrderIn.findAll"));
    }

    /**
     * Odstrani LineItem z databaze.
     * Tato metoda je umistena v OrderInDAO, protoze LineItems jsou zcela zavisle na svych objednavkach.
     * @param li LineItem k odstraneni
     */
    @Override public void removeLineItem(LineItem li) {
        currentSession().delete(li);
    }
}
