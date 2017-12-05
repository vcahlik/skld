package cz.cvut.fit.si1.skld.client.components.session_bar;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SessionBarFragmentHandler extends Handler {
    SessionBarFragment owner;

    @FXML
    private Label signedUser;

    public SessionBarFragmentHandler() {
        super(FXMLFragmentType.SESSION_BAR_FRAGMENT);
    }

    public void setOwner(SessionBarFragment owner) {
        this.owner = owner;
    }

    @FXML
    protected void initialize() {
        signedUser.setText(owner.getApp().getHttpClient().getLoggedInUser().getName());
    }

    @FXML
    private void handleLogoutLinkAction(ActionEvent event) {
        owner.logoutRequested();
    }
}
