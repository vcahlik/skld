package cz.cvut.fit.si1.skld.client.components.add_product_type_screen;

import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.navigator.NavigatorFrame;

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
