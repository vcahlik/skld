package cz.cvut.fit.project.skld.gui.components.login_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.util.concurrent.TimeUnit;

/**
 * Handler pro LoginScreen.
 */
public class LoginScreenHandler extends Handler {
    private LoginScreen owner;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label infoLabel;

    /**
     * Konstruktor.
     */
    public LoginScreenHandler() {
        super(FXMLFragmentType.LOGIN_SCREEN);
    }

    /**
     * Pripoji k handleru prislusny screen.
     * @param owner Fragment
     */
    public void setOwner(LoginScreen owner) {
        this.owner = owner;
    }

    /**
     * Zablokuje uzivateli na chvili policko pro zadani hesla.
     */
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
        owner.onPasswordSubmit(password);
    }
}
