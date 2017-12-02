package cz.cvut.fit.si1.skld.client.components.navigator.navigator_bar;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.FXMLFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigatorBarFragmentHandler extends Handler {
    private NavigatorBarFragment owner;

    public NavigatorBarFragmentHandler() {
        super(FXMLFragment.NAVIGATOR_BAR_FRAGMENT);
    }

    public void setOwner(NavigatorBarFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleGoBackLinkAction(ActionEvent event) {
        owner.goBackRequested();
    }
}
