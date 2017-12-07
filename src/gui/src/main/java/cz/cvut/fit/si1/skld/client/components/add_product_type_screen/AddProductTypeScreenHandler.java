package cz.cvut.fit.si1.skld.client.components.add_product_type_screen;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AddProductTypeScreenHandler extends Handler {
    private AddProductTypeScreen owner;

    @FXML
    private AnchorPane editProductType;

    public AddProductTypeScreenHandler() {
        super(FXMLFragmentType.ADD_PRODUCT_TYPE_SCREEN);
    }

    public void setOwner(AddProductTypeScreen owner) {
        this.owner = owner;
    }

    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        editProductType.getChildren().setAll((AnchorPane)editProductTypeFragment.getRoot());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.addProductType();
    }
}
