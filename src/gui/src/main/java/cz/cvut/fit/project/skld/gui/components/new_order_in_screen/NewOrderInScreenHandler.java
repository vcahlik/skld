package cz.cvut.fit.project.skld.gui.components.new_order_in_screen;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.components.edit_order_in.EditOrderInFragment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Handler NewOrderInScreenu.
 */
public class NewOrderInScreenHandler extends Handler {
    private NewOrderInScreen owner;

    @FXML
    private AnchorPane editOrderIn;

    /**
     * Konstruktor.
     */
    public NewOrderInScreenHandler() {
        super(FXMLFragmentType.NEW_ORDER_IN_SCREEN);
    }

    /**
     * Nastavi fragment pro editaci logisticke objednavky.
     * @param editOrderInFragment Fragment pro editaci logisticke objednavky
     */
    public void setEditOrderInFragment(EditOrderInFragment editOrderInFragment) {
        editOrderIn.getChildren().setAll((AnchorPane)editOrderInFragment.getRoot());
    }

    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        owner.onSubmit();
    }

    /**
     * Pripoji k handleru prislusny screen.
     * @param owner Fragment
     */
    public void setOwner(NewOrderInScreen owner) {
        this.owner = owner;
    }
}
