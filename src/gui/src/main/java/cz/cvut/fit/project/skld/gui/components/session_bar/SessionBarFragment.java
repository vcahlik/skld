package cz.cvut.fit.project.skld.gui.components.session_bar;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;

/**
 * Fragment listy, ktera zobrazuje informace o prihlasenem uzivateli a umoznuje mu odhlaseni.
 */
public class SessionBarFragment extends Fragment {
    SessionBarFragmentHandler handler;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public SessionBarFragment(Notifyable parent) {
        super(parent);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new SessionBarFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zpracuje pozadavek uzivatele o odhlaseni
     */
    public void onLogoutRequested() {
        getParent().notify(this, NotifyType.LOGOUT);
    }

}
