package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class EditOrderInFragmentHandler extends Handler {
    EditOrderInFragment owner;

    @FXML
    private VBox productsVBox;

    public EditOrderInFragmentHandler() {
        super(FXMLFragmentType.EDIT_ORDER_IN_FRAGMENT);
    }

    public void setOwner(EditOrderInFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleAddProductButtonAction(ActionEvent event) {
        OrderInProductFragment frag = new OrderInProductFragment(owner);
        productsVBox.getChildren().add(frag.getRoot());
    }
}
