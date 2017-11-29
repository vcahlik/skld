package cz.cvut.fit.si1.skld.client.view.fxview.handlers;

import cz.cvut.fit.si1.skld.client.controller.AppController;
import cz.cvut.fit.si1.skld.client.controller.screen_controllers.LoginController;
import cz.cvut.fit.si1.skld.client.view.fxview.FXHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import java.io.IOException;

public class LoginScreenHandler implements FXHandler {
    private LoginController controller;

    @Override
    public void setController(AppController appController) {
        if (this.controller != null) {
            throw new IllegalStateException("Controller can only be set once");
        }

        this.controller = new LoginController(appController);
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        if (passwordField != null) {
            String password = passwordField.getText();
            controller.submitPassword(password);
        }
    }

}
