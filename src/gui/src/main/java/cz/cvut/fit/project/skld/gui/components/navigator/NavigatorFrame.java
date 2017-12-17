package cz.cvut.fit.project.skld.gui.components.navigator;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar.NavigatorBarFragment;
import cz.cvut.fit.project.skld.gui.components.session_bar.SessionBarFragment;
import cz.cvut.fit.project.skld.*;

/**
 * Wrapper, ktery umoznuje navigaci a pridava okolo vestaveneho screenu navigacni listu a session bar.
 * Navigace probiha zpravidla o krok o jednu obrazovku dopredu nebo zpet (jako ve webovem prohlizeci).
 */
public abstract class NavigatorFrame extends Frame {
    private NavigatorFrameHandler handler;

    private NavigatorBarFragment navigatorBarFragment;
    private Screen content;
    private SessionBarFragment sessionBarFragment;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public NavigatorFrame(Passable source) {
        super(source);

        setNavigatorBarFragment(new NavigatorBarFragment(this));
        setSessionBarFragment(new SessionBarFragment(this));
    }

    /**
     * Vrati rizeni rodicovskemu objektu.
     * @param source Dcerinny objekt
     * @param result Vysledek provadeni operaci dcerinneho objektu
     */
    @Override
    public void pass(UI source, PassResult result) {
        super.pass(source, result);

        getSource().pass(this, result);
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new NavigatorFrameHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Nahradi aktualne zobrazenou obrazovku za jinou.
     * @param screen Nova obrazovka
     */
    @Override
    public void changeContent(Screen screen) {
        content = screen;
        handler.setContent(screen);
    }

    /**
     * Zachyti a zpracuje notifikace o udalostech v dcerinnych objektech.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (notifyType == NotifyType.LOGOUT) {
            getSource().pass(this, PassResult.LOGOUT);
        } else if (source == navigatorBarFragment & notifyType == NotifyType.USER_ACTION) {
            getSource().pass(this, PassResult.CANCELED);
        }
    }

    /**
     * Nastavi fragment s navigacni listou.
     * @param navigatorBarFragment Fragment s navigacni listou
     */
    public void setNavigatorBarFragment(NavigatorBarFragment navigatorBarFragment) {
        this.navigatorBarFragment = navigatorBarFragment;
        handler.setNavigatorBarFragment(navigatorBarFragment);
    }

    /**
     * Nastavi SessionBarFragment s informacemi o uzivateli.
     * @param sessionBarFragment SessionBarFragment
     */
    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        this.sessionBarFragment = sessionBarFragment;
        handler.setSessionBarFragment(sessionBarFragment);
    }
}
