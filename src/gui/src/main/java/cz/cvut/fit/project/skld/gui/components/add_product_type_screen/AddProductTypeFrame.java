package cz.cvut.fit.project.skld.gui.components.add_product_type_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.*;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

public class AddProductTypeFrame extends NavigatorFrame {
    public AddProductTypeFrame(Passable source) {
        super(source);
    }

    @Override
    public void follow() {
        super.follow();

        Screen screen = new AddProductTypeScreen(this);
        screen.follow();
    }
}
