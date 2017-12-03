package cz.cvut.fit.si1.skld.client.components.edit_product_type;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.domain.ProductType;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditProductTypeFragmentHandler extends Handler {
    private EditProductTypeFragment owner;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputName;

    public EditProductTypeFragmentHandler() {
        super(FXMLFragmentType.EDIT_PRODUCT_TYPE_FRAGMENT);
    }

    public void setOwner(EditProductTypeFragment owner) {
        this.owner = owner;
    }

    public void refresh() {
        inputID.setText(owner.getEditingProductType().getId());
        inputName.setText(owner.getEditingProductType().getName());

        inputID.setDisable(owner.isDisabled());
        inputName.setDisable(owner.isDisabled());
        if (!owner.isDisabled()) {
            inputID.setDisable(!owner.isIdEditEnabled());
        }
    }

    public String getID() {
        return inputID.getText();
    }

    public String getName() {
        return inputName.getText();
    }
}
