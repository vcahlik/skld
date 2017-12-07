package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Notifyable;

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
