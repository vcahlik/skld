package cz.cvut.fit.si1.skld.client.components.find_product_type;

import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class FindProductTypeFragmentHandler extends Handler {
    private FindProductTypeFragment owner;

    @FXML
    private TableView<ProductRepresentation> productTypeTable;

    @FXML
    private TableColumn<ProductRepresentation, Long> idColumn;
    @FXML
    private TableColumn<ProductRepresentation, String> nameColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
    }

    @FXML
    private void handleProductTypeClickedAction() {
        ProductRepresentation selected = productTypeTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            owner.setSelectedProductType(selected);
        }
    }

    public FindProductTypeFragmentHandler() {
        super(FXMLFragmentType.FIND_PRODUCT_TYPE_FRAGMENT);
    }

    public void setOwner(FindProductTypeFragment owner) {
        this.owner = owner;
    }

    public void setProductTypes(List<ProductRepresentation> productTypes) {
        productTypeTable.getItems().clear();
        for (ProductRepresentation productType : productTypes) {
            productTypeTable.getItems().add(productType);
        }
    }

    public void clearSelection() {
        productTypeTable.getSelectionModel().clearSelection();
    }
}
