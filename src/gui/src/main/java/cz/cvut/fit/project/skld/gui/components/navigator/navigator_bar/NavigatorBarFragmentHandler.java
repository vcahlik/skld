package cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Handler pro NavigatorBarFragment.
 */
public class NavigatorBarFragmentHandler extends Handler {
    private NavigatorBarFragment owner;

    /**
     * Konstruktor.
     */
    public NavigatorBarFragmentHandler() {
        super(FXMLFragmentType.NAVIGATOR_BAR_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(NavigatorBarFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleGoBackLinkAction(ActionEvent event) {
        owner.goBackRequested();
    }
}
