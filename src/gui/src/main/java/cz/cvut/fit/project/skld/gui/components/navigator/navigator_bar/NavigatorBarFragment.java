package cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;

/**
 * Fragment umoznujici navigaci o krok dopredu nebo zpet.
 */
public class NavigatorBarFragment extends Fragment {
    NavigatorBarFragmentHandler handler;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public NavigatorBarFragment(Notifyable parent) {
        super(parent);
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new NavigatorBarFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zpracuje pozadavek uzivatele o presun o jednu obrazovku zpet.
     */
    public void goBackRequested() {
        getParent().notify(this, NotifyType.USER_ACTION);
    }
}
