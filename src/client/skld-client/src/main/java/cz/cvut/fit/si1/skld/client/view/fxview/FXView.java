package cz.cvut.fit.si1.skld.client.view.fxview;

import cz.cvut.fit.si1.skld.client.view.AppScreen;
import cz.cvut.fit.si1.skld.client.view.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FXView implements View {
    private FXApp app;

    public FXView(FXApp app) {
        this.app = app;
    }

    @Override
    public void show() {
        app.getStage().show();
    }

    @Override
    public void changeScreen(AppScreen screen) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(AppScreen.getFXMLFileName(screen)));

        Parent root;
        try {
            root = loader.load();
            FXHandler handler = loader.getController();
            handler.setController(app.getController());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return;
        }

        Scene scene = new Scene(root);
        app.getStage().setScene(scene);
    }
}
