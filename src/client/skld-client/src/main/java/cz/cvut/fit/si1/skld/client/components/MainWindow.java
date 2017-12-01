package cz.cvut.fit.si1.skld.client.components;

import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.login_screen.LoginScreen;
import javafx.stage.Stage;

public class MainWindow extends Window {
    public MainWindow(App app, Stage stage) {
        super(app, stage);
    }

    @Override
    public void follow() {
        Screen screen = new LoginScreen(this);
        changeContent(screen);
        screen.follow();
    }

    @Override
    public void pass(UI source, PassResult result) {

    }

}
