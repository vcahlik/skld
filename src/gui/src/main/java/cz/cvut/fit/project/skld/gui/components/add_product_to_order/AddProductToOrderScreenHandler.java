package cz.cvut.fit.project.skld.gui.components.add_product_to_order;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AddProductToOrderScreenHandler extends Handler {
    private AddProductToOrderScreen owner;

    @FXML
    private AnchorPane findProductType;

    @FXML
    private Button submitButton;

    public AddProductToOrderScreenHandler() {
        super(FXMLFragmentType.ADD_PRODUCT_TO_ORDER_SCREEN);
    }

    public void setOwner(AddProductToOrderScreen owner) {
        this.owner = owner;
    }

    public void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        AnchorPane pane = (AnchorPane)findProductTypeFragment.getRoot();
        FXUtil.setAnchor(pane, 0.0, 0.0, 0.0, 0.0);
        findProductType.getChildren().setAll(pane);
    }

    public void refresh() {
        submitButton.setDisable(!owner.isSelectEnabled());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.onSubmit();
    }
}
