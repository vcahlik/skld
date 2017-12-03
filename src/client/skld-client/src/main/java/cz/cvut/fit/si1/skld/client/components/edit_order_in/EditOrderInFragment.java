package cz.cvut.fit.si1.skld.client.components.edit_order_in;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Notifyable;

public class EditOrderInFragment extends Fragment {
    private EditOrderInFragmentHandler handler;

    public EditOrderInFragment(Notifyable parent) {
        super(parent);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditOrderInFragmentHandler();
        handler.setOwner(this);
        return handler;
    }
}
