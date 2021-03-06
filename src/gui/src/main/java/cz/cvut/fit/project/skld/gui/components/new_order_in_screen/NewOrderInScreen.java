package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.client.exceptions.BadRequestException;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.PassResult;
import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInChange;

import java.io.IOException;

/**
 * Obrazovka pro vytvoreni nove logisticke objednavky.
 */
public class NewOrderInScreen extends Screen {
    private NewOrderInScreenHandler handler;

    private EditOrderInFragment editOrderInFragment;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public NewOrderInScreen(Passable source) {
        super(source);

        setEditOrderInFragment(new EditOrderInFragment(this));
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new NewOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Nastavi fragment pro editaci logisticke objednavky.
     * @param editOrderInFragment Fragment pro editaci logisticke objednavky
     */
    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        this.editOrderInFragment = editOrderInFragment;
        handler.setEditOrderInFragment(editOrderInFragment);
    }

    /**
     * Zavolano pri odeslani objednavky uzivatelem. Provede validaci vstupu a pri uspechu odesle objednavku na server a vrati uzivatele do hlavniho menu, jinak zobrazi uzivateli alert s chybou.
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
            getApp().getClient().createOrderIn(orderInChange);
        } catch (BadRequestException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_ALREADY_EXISTS_ERROR_ALERT_TITLE, Texts.Alerts.ID_ALREADY_EXISTS_ERROR_ALERT_TEXT);
            return;
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        getSource().pass(this, PassResult.DONE);
    }
}
