package cz.cvut.fit.project.skld.gui.components.main_menu;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.add_product_type_screen.AddProductTypeFrame;
import cz.cvut.fit.project.skld.gui.components.change_order_in_screen.ChangeOrderInFrame;
import cz.cvut.fit.project.skld.gui.components.change_product_type_screen.ChangeProductTypeFrame;
import cz.cvut.fit.project.skld.gui.components.new_order_in_screen.NewOrderInFrame;
import cz.cvut.fit.project.skld.gui.components.session_bar.SessionBarFragment;
import cz.cvut.fit.project.skld.*;

/**
 * Obrazovka hlavniho menu aplikace. Z ni se uzivatel muze jednim kliknutim dostat do implementace vetsiny use cases.
 */
public class MainMenuScreen extends Screen {
    MainMenuScreenHandler handler;
    private SessionBarFragment sessionBarFragment;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public MainMenuScreen(Passable source) {
        super(source);

        setSessionBarFragment(new SessionBarFragment(this));
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new MainMenuScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zachyti a zpracuje notifikace o udalostech v dcerinnych objektech.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
    @Override
    public void notify(UI source, NotifyType notifyType) {
        switch (notifyType) {
            case LOGOUT:
                getSource().pass(this, PassResult.LOGOUT);
                break;
        }
    }

    /**
     * Vrati rizeni obrazovce. Pokud byl duvodem k predani rizeni pozadavek pro odhlaseni, preda rizeni s timto duvodem rodici.
     * @param source Dcerinny objekt
     * @param result Vysledek provadeni operaci dcerinneho objektu
     */
    @Override
    public void pass(UI source, PassResult result) {
        super.pass(source, result);

        switch (result) {
            case LOGOUT:
                getSource().pass(this, PassResult.LOGOUT);
                break;
        }
    }

    /**
     * Presune uzivatele na screen vytvoreni nove logisticke objednavky.
     */
    public void newOrderIn() {
        NewOrderInFrame frame = new NewOrderInFrame(this);
        frame.follow();
    }

    /**
     * Presune uzivatele na screen zmeny existujici logisticke objednavky.
     */
    public void changeOrderIn() {
        ChangeOrderInFrame frame = new ChangeOrderInFrame(this);
        frame.follow();
    }

    /**
     * Presune uzivatele na screen vytvoreni noveho typu produktu.
     */
    public void addProductType() {
        AddProductTypeFrame frame = new AddProductTypeFrame(this);
        frame.follow();
    }

    /**
     * Presune uzivatele na screen zmeny existujiciho typu produktu.
     */
    public void changeProductType() {
        ChangeProductTypeFrame frame = new ChangeProductTypeFrame(this);
        frame.follow();
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
