package cz.cvut.fit.project.skld.gui.components.add_product_type_screen;

import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.*;
import cz.cvut.fit.project.skld.gui.components.navigator.NavigatorFrame;

/**
 * Wrapper, pridava okolo screenu navigacni listu a session bar.
 */
public class AddProductTypeFrame extends NavigatorFrame {
    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public AddProductTypeFrame(Passable source) {
        super(source);
    }

    /**
     * Vytvori obrazovku pro pridani noveho typu produktu a preda ji rizeni.
     */
    @Override
    public void follow() {
        super.follow();

        Screen screen = new AddProductTypeScreen(this);
        screen.follow();
    }
}
