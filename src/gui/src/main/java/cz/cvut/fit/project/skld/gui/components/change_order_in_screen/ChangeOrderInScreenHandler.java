package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;

public class ChangeOrderInScreenHandler extends Handler {
    private ChangeOrderInScreen owner;

    public ChangeOrderInScreenHandler() {
        super(FXMLFragmentType.CHANGE_ORDER_IN_SCREEN);
    }

    public void setOwner(ChangeOrderInScreen owner) {
        this.owner = owner;
    }
}
