package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;

public class EditOrderInFragmentHandler extends Handler {
    EditOrderInFragment owner;

    public EditOrderInFragmentHandler() {
        super(FXMLFragmentType.EDIT_ORDER_IN_FRAGMENT);
    }

    public void setOwner(EditOrderInFragment owner) {
        this.owner = owner;
    }
}
