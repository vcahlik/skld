package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

/**
 * Wrapper, pridava okolo screenu navigacni listu a session bar.
 */
public class ChangeOrderInFrame extends NavigatorFrame {
    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public ChangeOrderInFrame(Passable source) {
        super(source);
    }

    /**
     * Vytvori obrazovku pro zmenu logistickych objednavek a preda ji rizeni.
     */
    @Override
    public void follow() {
        super.follow();

        Screen screen = new ChangeOrderInScreen(this);
        screen.follow();
    }
}
