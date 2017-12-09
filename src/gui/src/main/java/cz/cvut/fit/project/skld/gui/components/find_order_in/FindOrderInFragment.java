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

public class FindOrderInFragment extends Fragment {
    private FindOrderInFragmentHandler handler;

    private List<OrderInRepresentation> ordersIn;
    private OrderInRepresentation selectedOrderIn;

    public FindOrderInFragment(Notifyable parent) {
        super(parent);
        ordersIn = new ArrayList<>();
        refresh();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new FindOrderInFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public boolean isSelected() {
        return getSelected() != null;
    }

    public OrderInRepresentation getSelected() {
        return selectedOrderIn;
    }

    protected void setSelectedOrderIn(OrderInRepresentation selectedOrderIn) {
        if (this.selectedOrderIn != selectedOrderIn) {
            this.selectedOrderIn = selectedOrderIn;
            getParent().notify(this, NotifyType.CHANGE);
        }
    }

    public void refresh() {
        try {
            ordersIn = getApp().getHttpClient().getOrderIns();
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }
        handler.setOrdersIn(ordersIn);

        handler.clearSelection();
    }
}