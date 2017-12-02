package cz.cvut.fit.si1.skld.client.components.add_product_type_screen;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.components.edit_product_type_fragment.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.FXMLFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class AddProductTypeScreenHandler extends Handler {
    private AddProductTypeScreen owner;

    @FXML
    private HBox editProductType;

    public AddProductTypeScreenHandler() {
        super(FXMLFragment.ADD_PRODUCT_TYPE_SCREEN);
    }

    public void setOwner(AddProductTypeScreen owner) {
        this.owner = owner;
    }

    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        editProductType.getChildren().setAll((HBox)editProductTypeFragment.getRoot());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.addProductType();
    }
}
