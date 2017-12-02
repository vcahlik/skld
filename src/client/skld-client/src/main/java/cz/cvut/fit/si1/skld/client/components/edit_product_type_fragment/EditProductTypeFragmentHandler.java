package cz.cvut.fit.si1.skld.client.components.edit_product_type_fragment;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.FXMLFragment;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditProductTypeFragmentHandler extends Handler {
    private EditProductTypeFragment owner;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputName;

    public EditProductTypeFragmentHandler() {
        super(FXMLFragment.EDIT_PRODUCT_TYPE_FRAGMENT);
    }

    public void setOwner(EditProductTypeFragment owner) {
        this.owner = owner;
    }

    public String getID() {
        return inputID.getText();
    }

    public String getName() {
        return inputName.getText();
    }
}
