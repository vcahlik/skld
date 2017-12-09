package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import cz.cvut.fit.project.skld.gui.components.find_order_in.FindOrderInFragment;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.io.IOException;

public class ChangeOrderInScreen extends Screen {
    private ChangeOrderInScreenHandler handler;

    private FindOrderInFragment findOrderInFragment;
    private EditOrderInFragment editOrderInFragment;

    private boolean editEnabled;

    public ChangeOrderInScreen(Passable source) {
        super(source);

        setFindOrderInFragment(new FindOrderInFragment(this));
        setEditOrderInFragment(new EditOrderInFragment(this, false));

        reset();
    }

    private void reset() {
        findOrderInFragment.refresh();
        editOrderInFragment.reset();
        setEditEnabled(false);
        handler.refresh();
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
        orderInChange.setDeliveryDate(editOrderInFragment.getEditedDeliveryDate());
        try {
            orderInChange.setProducts(editOrderInFragment.getEditedProducts());
        } catch (InvalidInputException e) {
            return;
        }

        try {
            getApp().getHttpClient().updateOrderIn(orderInChange);
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        reset();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new ChangeOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == findOrderInFragment && notifyType == NotifyType.CHANGE) {
            if (findOrderInFragment.isSelected()) {
                editOrderInFragment.fill(findOrderInFragment.getSelected());
                setEditEnabled(true);
            }
        }
    }

    public void setFindOrderInFragment(FindOrderInFragment findOrderInFragment) {
        this.findOrderInFragment = findOrderInFragment;
        handler.setFindOrderInFragment(findOrderInFragment);
    }

    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        this.editOrderInFragment = editOrderInFragment;
        handler.setEditOrderInFragment(editOrderInFragment);
    }

    public boolean isEditEnabled() {
        return editEnabled;
    }

    public void setEditEnabled(boolean editEnabled) {
        this.editEnabled = editEnabled;
        editOrderInFragment.setEnabled(editEnabled);
        handler.refresh();
    }
}
