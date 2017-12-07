package cz.cvut.fit.project.skld.gui.components.change_product_type_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
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
