package cz.cvut.fit.project.skld.gui.components.session_bar;

import cz.cvut.fit.project.skld.client.SkldClient;
import cz.cvut.fit.project.skld.gui.App;
import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.representations.UserRepresentation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Handler SessionBarFragmentu
 */
public class SessionBarFragmentHandler extends Handler {
    private SessionBarFragment owner;

    @FXML
    private Label signedUser;

    /**
     * Konstruktor.
     */
    public SessionBarFragmentHandler() {
        super(FXMLFragmentType.SESSION_BAR_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(SessionBarFragment owner) {
        this.owner = owner;
    }

    /**
     * Vola JavaFX pri inicializaci handleru.
     */
    @FXML
    protected void initialize() {
        signedUser.setText(owner.getApp().getClient().getLoggedInUser().getName());
    }

    @FXML
    private void handleLogoutLinkAction(ActionEvent event) {
        owner.onLogoutRequested();
    }
}
