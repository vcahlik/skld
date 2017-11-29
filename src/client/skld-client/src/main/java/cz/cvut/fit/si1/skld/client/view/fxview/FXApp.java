package cz.cvut.fit.si1.skld.client.view.fxview;

import cz.cvut.fit.si1.skld.client.controller.AppController;
import cz.cvut.fit.si1.skld.client.controller.Controller;
import cz.cvut.fit.si1.skld.client.model.Model;
import cz.cvut.fit.si1.skld.client.view.AppScreen;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApp extends Application {
    private AppController controller;
    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        Model model = new Model();
        FXView view = new FXView(this);
        this.controller = new AppController(model, view);
        this.stage = stage;

        view.changeScreen(AppScreen.LOGIN);
        view.show();
    }

    public AppController getController() {
        return controller;
    }

    public Stage getStage() {
        return stage;
    }
}
