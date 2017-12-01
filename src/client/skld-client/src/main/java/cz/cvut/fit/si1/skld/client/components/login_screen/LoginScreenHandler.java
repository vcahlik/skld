package cz.cvut.fit.si1.skld.client.components.login_screen;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.resources.FXMLFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class LoginScreenHandler extends Handler {
    private LoginScreen owner;

    public LoginScreenHandler() {
        super(FXMLFragment.LOGIN_SCREEN);
    }

    public void setOwner(LoginScreen owner) {
        this.owner = owner;
    }

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws IOException {
        String password = passwordField.getText();
        owner.submitPassword(password);
    }

}
