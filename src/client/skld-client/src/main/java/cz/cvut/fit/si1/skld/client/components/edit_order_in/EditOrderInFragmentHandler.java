package cz.cvut.fit.si1.skld.client.components.edit_order_in;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;

public class EditOrderInFragmentHandler extends Handler {
    EditOrderInFragment owner;

    public EditOrderInFragmentHandler() {
        super(FXMLFragmentType.EDIT_ORDER_IN_FRAGMENT);
    }

    public void setOwner(EditOrderInFragment owner) {
        this.owner = owner;
    }
}
