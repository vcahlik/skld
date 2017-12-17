package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Handler pro EditOrderInFragment.
 */
public class EditOrderInFragmentHandler extends Handler {
    private EditOrderInFragment owner;

    @FXML
    private VBox productsVBox;

    @FXML
    private TextField inputID;

    @FXML
    private DatePicker inputDeliveryDate;

    @FXML
    private TextField inputSupplierName;

    @FXML
    private Button addProductButton;

    @FXML
    private void handleAddProductButtonAction(ActionEvent event) {
        owner.addProduct();
    }

    /**
     * Konstruktor.
     */
    public EditOrderInFragmentHandler() {
        super(FXMLFragmentType.EDIT_ORDER_IN_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(EditOrderInFragment owner) {
        this.owner = owner;
    }

    /**
     * Vrati objekt do pocatecniho stavu.
     */
    public void reset() {
        if (owner.isFilled()) {
            inputID.setText(Long.toString(owner.getId()));
        } else {
            inputID.setText("");
        }

        resetInputDeliveryDate();

        inputSupplierName.setText(owner.getSupplierName());

        inputID.setDisable(!owner.isEnabled() || !owner.isIdEditEnabled());
        inputSupplierName.setDisable(!owner.isEnabled());

        addProductButton.setDisable(!owner.isEnabled());

        productsVBox.getChildren().clear();
        for (OrderInProductFragment orderInProductFragment : owner.getOrderInProductFragments()) {
            addOrderInProductFragment(orderInProductFragment);
        }
    }

    private void resetInputDeliveryDate() {
        if (owner.isFilled()) {
            Date date = owner.getDeliveryDate();
            if (date != null) {
                LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                inputDeliveryDate.setValue(localDate);
            } else {
                inputDeliveryDate.getEditor().clear();
            }
        } else {
            inputDeliveryDate.getEditor().clear();
        }

        inputDeliveryDate.setDisable(!owner.isEnabled());
    }

    protected String getID() {
        return inputID.getText();
    }

    protected LocalDate getDeliveryDate() {
        return inputDeliveryDate.getValue();
    }

    protected String getSupplierName() {
        return inputSupplierName.getText();
    }

    /**
     * Prida fragment reprezentujici nejaky produkt.
     * @param fragment Fragment reprezentujici produkt
     */
    public void addOrderInProductFragment(OrderInProductFragment fragment) {
        productsVBox.getChildren().add(fragment.getRoot());
    }

    /**
     * Smaze fragment reprezentujici dany produkt.
     * @param fragment Fragment reprezentujici produkt
     */
    public void removeOrderInProductFragment(OrderInProductFragment fragment) {
        productsVBox.getChildren().remove(fragment.getRoot());
    }
}
