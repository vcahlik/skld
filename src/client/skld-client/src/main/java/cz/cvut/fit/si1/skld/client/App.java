package cz.cvut.fit.si1.skld.client;

import cz.cvut.fit.si1.skld.client.components.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application implements Passable {
    private Session session;
    private MainWindow mainWindow;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        this.session = new Session();

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

    public Session getSession() {
        return session;
    }

}
