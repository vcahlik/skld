package cz.cvut.fit.project.skld.gui.components.find_order_in;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment pro vyhledani existujiciho typu logisticke objednavky.
 */
public class FindOrderInFragment extends Fragment {
    private FindOrderInFragmentHandler handler;

    private List<OrderInRepresentation> ordersIn;
    private OrderInRepresentation selectedOrderIn;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public FindOrderInFragment(Notifyable parent) {
        super(parent);
        ordersIn = new ArrayList<>();
        refresh();
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new FindOrderInFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zjisti, zda uzivatel oznacil nejakou objednavku ze seznamu
     * @return true pokud existuje oznacena objednavka
     */
    public boolean isAnyOrderInSelected() {
        return getSelected() != null;
    }

    /**
     * Vrati uzivatelem oznacenou objednavku.
     * @return Oznacena objednavka
     */
    public OrderInRepresentation getSelected() {
        return selectedOrderIn;
    }

    /**
     * Oznaci objednavku jako oznacenou uzivatelem.
     * @param selectedOrderIn Oznacena objednavka
     */
    protected void setSelectedOrderIn(OrderInRepresentation selectedOrderIn) {
        if (this.selectedOrderIn != selectedOrderIn) {
            this.selectedOrderIn = selectedOrderIn;
            getParent().notify(this, NotifyType.CHANGE);
        }
    }

    /**
     * Nacte seznam vsech logistickych objednavek a zobrazi jej.
     */
    public void refresh() {
        try {
            ordersIn = getApp().getClient().getOrderIns();
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }
        handler.setOrdersIn(ordersIn);

        handler.clearSelection();
    }
}