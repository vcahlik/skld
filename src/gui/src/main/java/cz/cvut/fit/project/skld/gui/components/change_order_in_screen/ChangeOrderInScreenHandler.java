package cz.cvut.fit.project.skld.gui.components.change_order_in_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import cz.cvut.fit.project.skld.gui.components.find_order_in.FindOrderInFragment;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ChangeOrderInScreenHandler extends Handler {
    private ChangeOrderInScreen owner;

    @FXML
    private AnchorPane findOrderIn;

    @FXML
    private AnchorPane editOrderIn;

    @FXML
    private Button submitButton;

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.onSubmit();
    }

    public ChangeOrderInScreenHandler() {
        super(FXMLFragmentType.CHANGE_ORDER_IN_SCREEN);
    }

    public void setOwner(ChangeOrderInScreen owner) {
        this.owner = owner;
    }

    public void setFindOrderInFragment(FindOrderInFragment findOrderInFragment) {
        AnchorPane pane = (AnchorPane)findOrderInFragment.getRoot();
        FXUtil.setAnchor(pane, 0.0, 0.0, 0.0, 0.0);
        findOrderIn.getChildren().setAll(pane);
    }

    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        editOrderIn.getChildren().setAll((AnchorPane)editOrderInFragment.getRoot());
    }

    protected void refresh() {
        submitButton.setDisable(!owner.isEditEnabled());
    }
}
