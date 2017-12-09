package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OrderInProductFragmentHandler extends Handler {
    private OrderInProductFragment owner;

    @FXML
    private TextField productQuantity;

    @FXML
    private Label productId;

    @FXML
    private Label productName;

    public OrderInProductFragmentHandler() {
        super(FXMLFragmentType.ORDER_IN_PRODUCT_FRAGMENT);
    }

    public void setOwner(OrderInProductFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleRemoveLinkAction(ActionEvent event) {
        owner.requestDelete();
    }

    protected String getQuantity() {
        return productQuantity.getText();
    }

    protected void refresh() {
        productQuantity.setText(Long.toString(owner.getProductRepresentation().getQuantity()));
        productId.setText(Long.toString(owner.getProductRepresentation().getId()));
        productName.setText(owner.getProductRepresentation().getName());
    }
}
