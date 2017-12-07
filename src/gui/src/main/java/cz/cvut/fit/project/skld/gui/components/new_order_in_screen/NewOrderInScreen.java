package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;

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
