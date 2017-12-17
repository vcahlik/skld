package cz.cvut.fit.project.skld.gui.components;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.login_screen.LoginScreen;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.*;
import javafx.stage.Stage;

/**
 * Hlavni okno aplikace. Zobrazene po jejim spusteni.
 */
public class MainWindow extends Window {
    /**
     * Konstruktor
     * @param app Aplikace
     * @param stage JavaFX Stage
     */
    public MainWindow(App app, Stage stage) {
        super(app, stage, Texts.Windows.MAIN_WINDOW_TITLE);
        stage.setMaximized(true);
    }

    /**
     * Vytvori prihlasovaci obrazovku a preda ji rizeni.
     */
    @Override
    public void follow() {
        Screen screen = new LoginScreen(this);
        screen.follow();
    }

    /**
     * Zavolano po ukonceni cinnosti dcerinnych komponent. Preda rizeni zpet aplikaci.
     * @param source Dcerinny objekt
     * @param result Vysledek provadeni operaci dcerinneho objektu
     */
    @Override
    public void pass(UI source, PassResult result) {
        getApp().pass(this, result);
    }

}
