package cz.cvut.fit.project.skld.gui.components.change_product_type_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

/**
 * Wrapper, pridava okolo screenu navigacni listu a session bar.
 */
public class ChangeProductTypeFrame extends NavigatorFrame {
    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public ChangeProductTypeFrame(Passable source) {
        super(source);
    }

    /**
     * Vytvori obrazovku pro zmenu existujicich typu produktu a preda ji rizeni.
     */
    @Override
    public void follow() {
        super.follow();

        Screen screen = new ChangeProductTypeScreen(this);
        screen.follow();
    }
}
