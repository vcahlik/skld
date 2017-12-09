package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;

public class ChangeOrderInScreen extends Screen {
    private ChangeOrderInScreenHandler handler;

    public ChangeOrderInScreen(Passable source) {
        super(source);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new ChangeOrderInScreenHandler();
        handler.setOwner(this);
        return handler;
    }
}
