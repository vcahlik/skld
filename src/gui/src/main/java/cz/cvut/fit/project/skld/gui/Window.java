package cz.cvut.fit.project.skld.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Window extends Component implements Wrapper {
    private Stage stage;
    private Screen screen;

    public Window(Notifyable parent, Stage stage) {
        super(parent);

        this.stage = stage;
    }

    public Window(Notifyable parent, Stage stage, String title) {
        this(parent, stage);
        setTitle(title);
    }

    public void close() {
        stage.close();
    }

    public Screen getScreen() {
        return screen;
    }

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

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    protected Stage getStage() {
        return stage;
    }

}
