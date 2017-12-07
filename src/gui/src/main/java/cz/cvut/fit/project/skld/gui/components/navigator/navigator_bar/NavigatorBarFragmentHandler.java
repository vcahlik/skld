package cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavigatorBarFragmentHandler extends Handler {
    private NavigatorBarFragment owner;

    public NavigatorBarFragmentHandler() {
        super(FXMLFragmentType.NAVIGATOR_BAR_FRAGMENT);
    }

    public void setOwner(NavigatorBarFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleGoBackLinkAction(ActionEvent event) {
        owner.goBackRequested();
    }
}
