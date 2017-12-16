package cz.cvut.fit.project.skld.gui.components.add_product_type_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.components.edit_product_type.EditProductTypeFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Handler pro AddProductTypeScreen.
 */
public class AddProductTypeScreenHandler extends Handler {
    private AddProductTypeScreen owner;

    @FXML
    private AnchorPane editProductType;

    /**
     * Konstruktor.
     */
    public AddProductTypeScreenHandler() {
        super(FXMLFragmentType.ADD_PRODUCT_TYPE_SCREEN);
    }

    /**
     * Pripoji k handleru prislusny screen.
     * @param owner Fragment
     */
    public void setOwner(AddProductTypeScreen owner) {
        this.owner = owner;
    }

    /**
     * Nastavi EditProductTypeFragment pro editaci typu produktu.
     * @param editProductTypeFragment EditProductTypeFragment
     */
    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        editProductType.getChildren().setAll((AnchorPane)editProductTypeFragment.getRoot());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.onAddProductType();
    }
}
