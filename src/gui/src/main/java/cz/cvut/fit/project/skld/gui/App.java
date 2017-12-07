package cz.cvut.fit.project.skld.gui;

import cz.cvut.fit.project.skld.client.http.SkldHttpClient;
import cz.cvut.fit.project.skld.gui.components.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application implements Passable {
    private SkldHttpClient httpClient;
    private MainWindow mainWindow;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
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
