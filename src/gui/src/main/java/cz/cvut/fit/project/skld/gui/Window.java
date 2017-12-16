package cz.cvut.fit.project.skld.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Okno, ktere muze obsahovat nejakou obrazovku.
 */
public abstract class Window extends Component implements Wrapper {
    private Stage stage;
    private Screen screen;

    /**
     * Konstruktor
     * @param parent Rodicovsky objekt, ze ktereho bylo okno vytvoreno
     * @param stage JavaFX stage okna
     */
    public Window(Notifyable parent, Stage stage) {
        super(parent);

        this.stage = stage;
    }

    /**
     * Konstruktor
     * @param parent Rodicovsky objekt, ze ktereho bylo okno vytvoreno
     * @param stage JavaFX stage okna
     * @param title Text v zahlavi okna
     */
    public Window(Notifyable parent, Stage stage, String title) {
        this(parent, stage);
        setTitle(title);
    }

    /**
     * Zavre okno. Obvykle volano rodicovskym objektem pote, co mu okno preda zpet rizeni.
     */
    public void close() {
        stage.close();
    }

    /**
     * Vraci aktualni obrazovku.
     * @return Aktualni obrazovka
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     *
     * @param screen Nova obrazovka
     */
    public void changeContent(Screen screen) {
        this.screen = screen;

        Scene scene = stage.getScene();
        if (scene == null) {
            stage.setScene(new Scene(screen.getRoot()));
            stage.show();
        } else {
            scene.setRoot(screen.getRoot());
        }
    }

    /**
     * Zmeni text v zahlavi okna.
     * @param title Titulek okna
     */
    public void setTitle(String title) {
        stage.setTitle(title);
    }

    /**
     * Vraci JavaFX stage okna
     * @return JavaFX stage
     */
    protected Stage getStage() {
        return stage;
    }

}
