package cz.cvut.fit.si1.skld.client.components.main_menu;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.components.session_bar.SessionBarFragment;
import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainMenuScreenHandler extends Handler {
    private MainMenuScreen owner;

    @FXML
    private HBox sessionBar;

    public MainMenuScreenHandler() {
        super(FXMLFragmentType.MAIN_MENU_SCREEN);
    }

    public void setOwner(MainMenuScreen owner) {
        this.owner = owner;
    }

    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        sessionBar.getChildren().setAll((HBox)sessionBarFragment.getRoot());
    }

    @FXML
    private void handleNewOrderInButtonAction(ActionEvent event) {
        owner.newOrderIn();
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
