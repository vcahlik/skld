package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import cz.cvut.fit.project.skld.gui.components.find_order_in.FindOrderInFragment;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInChange;

import java.io.IOException;

/**
 * Obrazovka pro zobrazeni existujicich typu logistickych objednavek a jejich zmenu.
 */
public class ChangeOrderInScreen extends Screen {
    private ChangeOrderInScreenHandler handler;

    private FindOrderInFragment findOrderInFragment;
    private EditOrderInFragment editOrderInFragment;

    private boolean editEnabled;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public ChangeOrderInScreen(Passable source) {
        super(source);

        setFindOrderInFragment(new FindOrderInFragment(this));
        setEditOrderInFragment(new EditOrderInFragment(this, false));

        reset();
    }

    /**
     * Vrati objekt do pocatecniho stavu.
     */
    private void reset() {
        findOrderInFragment.refresh();
        editOrderInFragment.reset();
        setEditEnabled(false);
        handler.refresh();
    }

    /**
     * Zavolano pri odeslani formulare uzivatelem. Pri neplatnem ID uzivatele upozorni na chybu, jinak odesle zmenu na server.
     */
    protected void onSubmit() {
        Long id;
        try {
            id = editOrderInFragment.getEditedId();
        } catch (InvalidInputException e) {
            return;
        }
        String supplierName = editOrderInFragment.getEditedSupplierName();

        OrderInChange orderInChange = new OrderInChange(id, supplierName);
        orderInChange.setDeliveryDate(editOrderInFragment.getEditedDeliveryDate());
        try {
            orderInChange.setProducts(editOrderInFragment.getEditedProducts());
        } catch (InvalidInputException e) {
            return;
        }

        try {
            getApp().getClient().updateOrderIn(orderInChange);
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        reset();
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new ChangeOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zachyti a zpracuje notifikace o udalostech v dcerinnych objektech.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == findOrderInFragment && notifyType == NotifyType.CHANGE) {
            if (findOrderInFragment.isAnyOrderInSelected()) {
                editOrderInFragment.fill(findOrderInFragment.getSelected());
                setEditEnabled(true);
            }
        }
    }

    /**
     * Nastavi FindOrderInFragment pro vyhledani logisticke objednavky.
     * @param findOrderInFragment FindOrderInFragment
     */
    public void setFindOrderInFragment(FindOrderInFragment findOrderInFragment) {
        this.findOrderInFragment = findOrderInFragment;
        handler.setFindOrderInFragment(findOrderInFragment);
    }

    /**
     * Nastavi EditOrderInFragment pro editaci existujici logisticke objednavky.
     * @param editOrderInFragment EditOrderInFragment
     */
    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        this.editOrderInFragment = editOrderInFragment;
        handler.setEditOrderInFragment(editOrderInFragment);
    }

    /**
     * Zjisti, zda je uzivateli umoznena editace zobrazenych hodnot.
     * @return true editace umoznena
     */
    public boolean isEditEnabled() {
        return editEnabled;
    }

    /**
     * Nastavi, zda je uzivateli umoznena editace zobrazenych hodnot.
     * @param editEnabled true editace bude umoznena
     */
    public void setEditEnabled(boolean editEnabled) {
        this.editEnabled = editEnabled;
        editOrderInFragment.setEnabled(editEnabled);
        handler.refresh();
    }
}
