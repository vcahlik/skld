package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

public class ChangeOrderInFrame extends NavigatorFrame {
    public ChangeOrderInFrame(Passable source) {
        super(source);
    }

    @Override
    public void follow() {
        super.follow();

        Screen screen = new ChangeOrderInScreen(this);
        screen.follow();
    }
}
