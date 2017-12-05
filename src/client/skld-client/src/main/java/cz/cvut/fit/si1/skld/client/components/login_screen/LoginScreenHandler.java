package cz.cvut.fit.si1.skld.client.components.login_screen;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.util.concurrent.TimeUnit;

public class LoginScreenHandler extends Handler {
    private LoginScreen owner;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label infoLabel;

    public LoginScreenHandler() {
        super(FXMLFragmentType.LOGIN_SCREEN);
    }

    public void setOwner(LoginScreen owner) {
        this.owner = owner;
    }

    protected void onInvalidPassword() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
        }

        infoLabel.setVisible(true);
        passwordField.requestFocus();
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        String password = passwordField.getText();
        passwordField.clear();
        infoLabel.setVisible(false);
        owner.submitPassword(password);
    }
}
