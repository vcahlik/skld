package cz.cvut.fit.si1.skld.client.components.change_product_type_screen;

import cz.cvut.fit.si1.skld.client.Passable;
import cz.cvut.fit.si1.skld.client.Screen;
import cz.cvut.fit.si1.skld.client.components.navigator.NavigatorFrame;

public class ChangeProductTypeFrame extends NavigatorFrame {
    public ChangeProductTypeFrame(Passable source) {
        super(source);
    }

    @Override
    public void follow() {
        super.follow();

        Screen screen = new ChangeProductTypeScreen(this);
        screen.follow();
    }
}
