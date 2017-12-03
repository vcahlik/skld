package cz.cvut.fit.si1.skld.client.components.new_order_in_screen;

import cz.cvut.fit.si1.skld.client.Passable;
import cz.cvut.fit.si1.skld.client.Screen;
import cz.cvut.fit.si1.skld.client.components.navigator.NavigatorFrame;

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
