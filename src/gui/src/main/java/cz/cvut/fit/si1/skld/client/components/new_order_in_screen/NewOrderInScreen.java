package cz.cvut.fit.si1.skld.client.components.new_order_in_screen;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Passable;
import cz.cvut.fit.si1.skld.client.Screen;
import cz.cvut.fit.si1.skld.client.components.edit_order_in.EditOrderInFragment;

public class NewOrderInScreen extends Screen {
    private NewOrderInScreenHandler handler;

    private EditOrderInFragment editOrderInFragment;

    public NewOrderInScreen(Passable source) {
        super(source);

        setEditOrderInFragment(new EditOrderInFragment(this));
    }

    @Override
    public Handler makeHandler() {
        this.handler = new NewOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        this.editOrderInFragment = editOrderInFragment;
        handler.setEditOrderInFragment(editOrderInFragment);
    }
}
