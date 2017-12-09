package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EditOrderInFragmentHandler extends Handler {
    private EditOrderInFragment owner;

    @FXML
    private VBox productsVBox;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputSupplierName;

    public EditOrderInFragmentHandler() {
        super(FXMLFragmentType.EDIT_ORDER_IN_FRAGMENT);
    }

    public void setOwner(EditOrderInFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleAddProductButtonAction(ActionEvent event) {
        owner.addProduct();
    }

    protected String getID() {
        return inputID.getText();
    }

    protected String getSupplierName() {
        return inputSupplierName.getText();
    }

    public void addOrderInProductFragment(OrderInProductFragment fragment) {
        productsVBox.getChildren().add(fragment.getRoot());
    }

    public void removeOrderInProductFragment(OrderInProductFragment fragment) {
        productsVBox.getChildren().remove(fragment.getRoot());
    }
}
