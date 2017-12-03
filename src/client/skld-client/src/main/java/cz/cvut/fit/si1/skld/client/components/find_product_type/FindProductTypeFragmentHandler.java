package cz.cvut.fit.si1.skld.client.components.find_product_type;

import cz.cvut.fit.si1.skld.client.FXMLFragmentType;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.domain.ProductType;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class FindProductTypeFragmentHandler extends Handler {
    private FindProductTypeFragment owner;

    @FXML
    private TableView<ProductType> productTypeTable;

    @FXML
    private TableColumn<ProductType, String> idColumn;
    @FXML
    private TableColumn<ProductType, String> nameColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getId()));
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
    }

    @FXML
    private void handleProductTypeClickedAction() {
        ProductType selected = productTypeTable.getSelectionModel().getSelectedItem();
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

    public void setProductTypes(List<ProductType> productTypes) {
        productTypeTable.getItems().clear();
        for (ProductType productType : productTypes) {
            productTypeTable.getItems().add(productType);
        }
    }

    public void clearSelection() {
        productTypeTable.getSelectionModel().clearSelection();
    }
}
