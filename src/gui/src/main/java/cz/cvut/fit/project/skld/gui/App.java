package cz.cvut.fit.project.skld.gui;

import cz.cvut.fit.project.skld.client.http.SkldHttpClient;
import cz.cvut.fit.project.skld.gui.components.MainWindow;
import cz.cvut.fit.project.skld.gui.resources.Config;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Nejvyssi trida a vstupni bod aplikace. Dedi tridu Application definovanou knihovnou JavaFX.
 */
public class App extends Application implements Passable {
    private SkldHttpClient httpClient;
    private MainWindow mainWindow;

    /**
     * Hlavni metoda. Nacte cestu ke konfiguracnimu souboru a spusti JavaFX aplikaci metodou launch()
     * @param args Argumenty predane z prikazove radky: cesta ke konfiguracnimu souboru
     */
    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println(Texts.ConsoleOutput.USAGE_HINT);
            System.exit(1);
        } else if (args.length == 1) {
            Config.setConfigFilePath(args[0]);
        } else {
            System.err.println(Texts.ConsoleOutput.NO_CONFIG_FILE_SPECIFIED_MESSAGE);
        }
        launch(args);
    }

    /**
     * Vstupni metoda knihovny JavaFX. Vytvori hlavni okno a preda mu kontrolu.
     * @param stage Automaticky vytvoreny JavaFX stage
     */
    public void start(Stage stage) {
        mainWindow = new MainWindow(this, stage);
        mainWindow.follow();
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {

    }

    @Override
    public void follow() {

    }

    @Override
    public void pass(UI source, PassResult result) {

    }

    @Override
    public App getApp() {
        return this;
    }

    @Override
    public void changeContent(Screen screen) {

    }

    public void setHttpClient(SkldHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public SkldHttpClient getHttpClient() {
        return httpClient;
    }

}
