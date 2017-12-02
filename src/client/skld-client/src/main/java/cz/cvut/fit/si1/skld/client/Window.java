package cz.cvut.fit.si1.skld.client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Window extends Component implements Wrapper {
    private Passable source;
    private App app;
    private Stage stage;
    private Screen screen;

    public Window(Passable source, Stage stage) {
        super(source.getApp());

        this.source = source;
        this.stage = stage;
    }

    public Window(Passable source, Stage stage, String title) {
        this(source, stage);
        setTitle(title);
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {

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

}
