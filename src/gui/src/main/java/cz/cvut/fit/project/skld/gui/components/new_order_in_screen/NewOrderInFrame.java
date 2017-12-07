package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

public class NewOrderInFrame extends NavigatorFrame {
    public NewOrderInFrame(Passable source) {
        super(source);
    }

    @Override
    public void follow() {
        super.follow();

        Screen screen = new NewOrderInScreen(this);
        screen.follow();
    }
}
