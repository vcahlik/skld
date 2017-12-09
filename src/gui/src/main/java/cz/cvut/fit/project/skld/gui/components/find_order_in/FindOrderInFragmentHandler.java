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

    public FindOrderInFragmentHandler() {
        super(FXMLFragmentType.FIND_ORDER_IN_FRAGMENT);
    }

    public void setOwner(FindOrderInFragment owner) {
        this.owner = owner;
    }

    public void setOrdersIn(List<OrderInRepresentation> productTypes) {
        orderInTable.getItems().clear();
        for (OrderInRepresentation productType : productTypes) {
            orderInTable.getItems().add(productType);
        }
    }

    public void clearSelection() {
        orderInTable.getSelectionModel().clearSelection();
    }
}