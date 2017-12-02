package cz.cvut.fit.si1.skld.client.components.edit_product_type_fragment;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Notifyable;

public class EditProductTypeFragment extends Fragment {
    EditProductTypeFragmentHandler handler;

    public EditProductTypeFragment(Notifyable parent) {
        super(parent);
    }

    public String getID() {
        return handler.getID();
    }

    public String getName() {
        return handler.getName();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }
}
