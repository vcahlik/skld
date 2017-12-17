package cz.cvut.fit.project.skld.gui.components.find_product_type;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
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

    /**
     * Vola JavaFX pri inicializaci handleru.
     */
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

    /**
     * Konstruktor.
     */
    public FindProductTypeFragmentHandler() {
        super(FXMLFragmentType.FIND_PRODUCT_TYPE_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(FindProductTypeFragment owner) {
        this.owner = owner;
    }

    /**
     * Zobrazi seznam typu produktu.
     * @param productTypes Typy produktu ke zobrazeni
     */
    public void setProductTypes(List<ProductRepresentation> productTypes) {
        productTypeTable.getItems().clear();
        for (ProductRepresentation productType : productTypes) {
            productTypeTable.getItems().add(productType);
        }
    }

    /**
     * Zadny polozky oznacene uzivatelem jiz nebudou oznaceny.
     */
    public void clearSelection() {
        productTypeTable.getSelectionModel().clearSelection();
    }
}
