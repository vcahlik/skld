package cz.cvut.fit.si1.skld.client.components.login_screen;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class LoginScreenHandler extends Handler {
    private LoginScreen owner;

    @FXML
    private PasswordField passwordField;

    public LoginScreenHandler() {
        super(FXMLFragmentType.LOGIN_SCREEN);
    }

    public void setOwner(LoginScreen owner) {
        this.owner = owner;
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        String password = passwordField.getText();
        passwordField.clear();
        owner.submitPassword(password);
    }
}
