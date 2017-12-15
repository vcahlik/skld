package cz.cvut.fit.project.skld.gui;

import cz.cvut.fit.project.skld.client.http.SkldHttpClient;
import cz.cvut.fit.project.skld.gui.components.MainWindow;
import cz.cvut.fit.project.skld.gui.resources.Config;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application implements Passable {
    private SkldHttpClient httpClient;
    private MainWindow mainWindow;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar gui.jar configfile");
            System.exit(1);
        }
        Config.setConfigFilePath(args[0]);
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
