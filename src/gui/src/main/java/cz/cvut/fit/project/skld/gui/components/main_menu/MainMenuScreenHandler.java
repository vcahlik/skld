package cz.cvut.fit.project.skld.gui.components.main_menu;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.components.session_bar.SessionBarFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 * Handler pro MainMenuScreen.
 */
public class MainMenuScreenHandler extends Handler {
    private MainMenuScreen owner;

    @FXML
    private HBox sessionBar;

    /**
     * Konstruktor.
     */
    public MainMenuScreenHandler() {
        super(FXMLFragmentType.MAIN_MENU_SCREEN);
    }

    /**
     * Pripoji k handleru prislusny screen.
     * @param owner Fragment
     */
    public void setOwner(MainMenuScreen owner) {
        this.owner = owner;
    }

    /**
     * Nastavi SessionBarFragment s informacemi o uzivateli.
     * @param sessionBarFragment SessionBarFragment
     */
    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        sessionBar.getChildren().setAll((HBox)sessionBarFragment.getRoot());
    }

    @FXML
    private void handleNewOrderInButtonAction(ActionEvent event) {
        owner.newOrderIn();
    }

    @FXML
    private void handleChangeOrderInButtonAction(ActionEvent event) {
        owner.changeOrderIn();
    }

    @FXML
    private void handleAddProductTypeButtonAction(ActionEvent event) {
        owner.addProductType();
    }

    @FXML
    private void handleChangeProductTypeButtonAction(ActionEvent event) {
        owner.changeProductType();
    }
}
