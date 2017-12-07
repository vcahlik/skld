package cz.cvut.fit.project.skld.gui.components;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.login_screen.LoginScreen;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.*;
import javafx.stage.Stage;

public class MainWindow extends Window {
    public MainWindow(App app, Stage stage) {
        super(app, stage, Texts.Windows.MAIN_WINDOW_TITLE);
    }

    @Override
    public void follow() {
        Screen screen = new LoginScreen(this);
        screen.follow();
    }

    @Override
    public void pass(UI source, PassResult result) {

    }

}
