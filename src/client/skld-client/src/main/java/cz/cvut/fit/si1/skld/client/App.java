package cz.cvut.fit.si1.skld.client;

import cz.cvut.fit.project.skld.representations.LogInDetails;
import cz.cvut.fit.project.skld.representations.UserRepresentation;
import cz.cvut.fit.si1.skld.client.components.MainWindow;
import cz.cvut.fit.si1.skld.client.network.exceptions.APIException;
import cz.cvut.fit.si1.skld.client.network.http.SkldHttpClient;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application implements Passable {
    private Session session;
    private MainWindow mainWindow;

    public static void main(String[] args) throws IOException, APIException {
        UserRepresentation user = SkldHttpClient.getClientForPIN("http://localhost:8080", "1234").getLoggedInUser();
        System.out.println(user.getName());
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
