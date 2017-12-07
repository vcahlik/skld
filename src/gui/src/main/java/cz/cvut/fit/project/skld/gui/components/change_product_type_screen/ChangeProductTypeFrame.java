package cz.cvut.fit.project.skld.gui.components.change_product_type_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

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
