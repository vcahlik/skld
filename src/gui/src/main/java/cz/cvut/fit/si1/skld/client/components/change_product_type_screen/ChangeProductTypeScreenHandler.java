package cz.cvut.fit.si1.skld.client.components.change_product_type_screen;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.si1.skld.client.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ChangeProductTypeScreenHandler extends Handler {
    private ChangeProductTypeScreen owner;

    @FXML
    private AnchorPane findProductType;

    @FXML
    private AnchorPane editProductType;

    @FXML
    private Button submitButton;

    public ChangeProductTypeScreenHandler() {
        super(FXMLFragmentType.CHANGE_PRODUCT_TYPE_SCREEN);
    }

    public void setOwner(ChangeProductTypeScreen owner) {
        this.owner = owner;
    }

    public void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        AnchorPane pane = (AnchorPane)findProductTypeFragment.getRoot();
        FXUtil.setAnchor(pane, 0.0, 0.0, 0.0, 0.0);
        findProductType.getChildren().setAll(pane);
    }

    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        editProductType.getChildren().setAll((AnchorPane)editProductTypeFragment.getRoot());
    }

    public void refresh() {
        submitButton.setDisable(!owner.isEditEnabled());
        submitButton.setDefaultButton(true);
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.submitChangedProductType();
    }
}
