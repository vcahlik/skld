package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Notifyable;

public class OrderInProductFragment extends Fragment {
    private OrderInProductFragmentHandler handler;

    public OrderInProductFragment(Notifyable parent) {
        super(parent);
    }

    @Override
    public Handler makeHandler() {
        OrderInProductFragmentHandler handler = new OrderInProductFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

}
