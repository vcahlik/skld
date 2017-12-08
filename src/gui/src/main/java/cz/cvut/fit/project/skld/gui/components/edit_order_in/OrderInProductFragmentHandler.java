package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;

public class OrderInProductFragmentHandler extends Handler {
    private OrderInProductFragment owner;

    public OrderInProductFragmentHandler() {
        super(FXMLFragmentType.ORDER_IN_PRODUCT_FRAGMENT);
    }

    public void setOwner(OrderInProductFragment owner) {
        this.owner = owner;
    }
}
