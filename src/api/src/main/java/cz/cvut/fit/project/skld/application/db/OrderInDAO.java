package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.LineItem;
import cz.cvut.fit.project.skld.application.core.OrderIn;

import java.util.List;
import java.util.Optional;

/**
 * Implementuje zakladni databazove operace nad OrderIn objekty.
 */
public interface OrderInDAO {
    /**
     * Vraci logistickou objednavku se zadanym ID.
     * @param id ID
     * @return Logisticka objednavka
     */
    Optional<OrderIn> findById(Long id);

    /**
     * Vlozi do databaze novou logistickou objednavku.
     * @param order Logisticka objednavka
     * @return Vytvorena logisticka objednavka (vcetne pripadnych automaticky generovanych hodnot)
     */
    OrderIn create(OrderIn order);

    /**
     * Vraci seznam vsech logistickych objednavek.
     * @return Seznam objednavek
     */
    List<OrderIn> findAll();

    /**
     * Odstrani LineItem z databaze.
     * Tato metoda je umistena v OrderInDAO, protoze LineItems jsou zcela zavisle na svych objednavkach.
     * @param li LineItem k odstraneni
     */
    void removeLineItem(LineItem li);
}
