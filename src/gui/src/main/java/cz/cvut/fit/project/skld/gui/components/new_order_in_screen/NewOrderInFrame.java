package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

/**
 * Wrapper, pridava okolo screenu navigacni listu a session bar.
 */
public class NewOrderInFrame extends NavigatorFrame {
    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
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
