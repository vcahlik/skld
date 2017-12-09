package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.PassResult;
import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInChange;

import java.io.IOException;

public class NewOrderInScreen extends Screen {
    private NewOrderInScreenHandler handler;

    private EditOrderInFragment editOrderInFragment;

    public NewOrderInScreen(Passable source) {
        super(source);

        setEditOrderInFragment(new EditOrderInFragment(this));
    }

    @Override
    public Handler makeHandler() {
        this.handler = new NewOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        this.editOrderInFragment = editOrderInFragment;
        handler.setEditOrderInFragment(editOrderInFragment);
    }

    protected void onSubmit() {
        Long id;
        try {
            id = editOrderInFragment.getEditedId();
        } catch (InvalidInputException e) {
            return;
        }
        String supplierName = editOrderInFragment.getEditedSupplierName();

        OrderInChange orderInChange = new OrderInChange(id, supplierName);
        try {
            orderInChange.setProducts(editOrderInFragment.getEditedProducts());
        } catch (InvalidInputException e) {
            return;
        }

        try {
            getApp().getHttpClient().createOrderIn(orderInChange);
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        getSource().pass(this, PassResult.DONE);
    }
}
