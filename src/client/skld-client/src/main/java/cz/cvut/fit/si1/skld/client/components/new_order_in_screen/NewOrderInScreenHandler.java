package cz.cvut.fit.si1.skld.client.components.new_order_in_screen;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.components.edit_order_in.EditOrderInFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class NewOrderInScreenHandler extends Handler {
    private NewOrderInScreen owner;

    @FXML
    private AnchorPane editOrderIn;

    public NewOrderInScreenHandler() {
        super(FXMLFragmentType.NEW_ORDER_IN_SCREEN);
    }

    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        editOrderIn.getChildren().setAll((AnchorPane)editOrderInFragment.getRoot());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        System.out.println("prdel");
    }

    public void setOwner(NewOrderInScreen owner) {
        this.owner = owner;
    }
}
