package cz.cvut.fit.si1.skld.client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Window extends Component implements Passable {
    private Passable source;
    private App app;
    private Stage stage;
    private Screen screen;

    public Window(Passable source, Stage stage) {
        this.source = source;
        this.app = source.getApp();
        this.stage = stage;
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {

    }

    @Override
    public App getApp() {
        return this.app;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;

        Scene scene = stage.getScene();
        if (scene == null) {
            stage.setScene(new Scene(screen.getRoot()));
            stage.show();
        } else {
            scene.setRoot(screen.getRoot());
        }
    }

}
