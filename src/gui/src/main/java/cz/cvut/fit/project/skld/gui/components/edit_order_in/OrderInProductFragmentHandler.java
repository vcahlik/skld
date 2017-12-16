package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Handler pro OrderInProductFragment.
 */
public class OrderInProductFragmentHandler extends Handler {
    private OrderInProductFragment owner;

    @FXML
    private TextField productQuantity;

    @FXML
    private Label productId;

    @FXML
    private Label productName;

    /**
     * Konstruktor.
     */
    public OrderInProductFragmentHandler() {
        super(FXMLFragmentType.ORDER_IN_PRODUCT_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(OrderInProductFragment owner) {
        this.owner = owner;
    }

    @FXML
    private void handleRemoveLinkAction(ActionEvent event) {
        owner.requestDelete();
    }

    /**
     * Vraci uzivatelem zadane mnozstvi produktu.
     * @return Mnozstvi
     */
    protected String getQuantity() {
        return productQuantity.getText();
    }

    /**
     * Aktualizuje zobrazene hodnoty.
     */
    protected void refresh() {
        productQuantity.setText(Long.toString(owner.getProductRepresentation().getQuantity()));
        productId.setText(Long.toString(owner.getProductRepresentation().getId()));
        productName.setText(owner.getProductRepresentation().getName());
    }
}
