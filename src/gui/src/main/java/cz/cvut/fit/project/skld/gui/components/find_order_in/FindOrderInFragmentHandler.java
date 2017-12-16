package cz.cvut.fit.project.skld.gui.components.find_order_in;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Handler pro FindOrderInFragment.
 */
public class FindOrderInFragmentHandler extends Handler {
    private FindOrderInFragment owner;

    @FXML
    private TableView<OrderInRepresentation> orderInTable;

    @FXML
    private TableColumn<OrderInRepresentation, Long> idColumn;

    @FXML
    private TableColumn<OrderInRepresentation, String> supplierNameColumn;

    @FXML
    private TableColumn<OrderInRepresentation, String> createdByColumn;

    @FXML
    private TableColumn<OrderInRepresentation, String> stateColumn;

    /**
     * Vola JavaFX pri inicializaci handleru.
     */
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        supplierNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSupplierName()));
        createdByColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCreatedBy().getName()));
        stateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getState()));
    }

    @FXML
    private void handleOrderInClickedAction() {
        OrderInRepresentation selected = orderInTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            owner.setSelectedOrderIn(selected);
        }
    }

    /**
     * Konstruktor.
     */
    public FindOrderInFragmentHandler() {
        super(FXMLFragmentType.FIND_ORDER_IN_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(FindOrderInFragment owner) {
        this.owner = owner;
    }

    /**
     * Zobrazi seznam logistickych objednavek.
     * @param productTypes Logisticke objednavky ke zobrazeni
     */
    public void setOrdersIn(List<OrderInRepresentation> productTypes) {
        orderInTable.getItems().clear();
        for (OrderInRepresentation productType : productTypes) {
            orderInTable.getItems().add(productType);
        }
    }

    /**
     * Zadny polozky oznacene uzivatelem jiz nebudou oznaceny.
     */
    public void clearSelection() {
        orderInTable.getSelectionModel().clearSelection();
    }
}