package cz.cvut.fit.project.skld.gui.components.add_product_to_order;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

/**
 * Okno obsahujici obrazovku pro vybrani typu produktu k zarazeni do objednavky.
 */
public class AddProductToOrderWindow extends Window {
    private AddProductToOrderScreen addProductToOrderScreen;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public AddProductToOrderWindow(Notifyable parent) {
        super(parent, new Stage(), Texts.Windows.ADD_PRODUCT_WINDOW_TITLE);

        getStage().initModality(Modality.APPLICATION_MODAL);
        addProductToOrderScreen = new AddProductToOrderScreen(this);
    }

    /**
     * Zadane typy produktu nebudou zobrazeny v seznamu.
     * @param excludedProductTypes Exkludovane typy
     */
    public void setExcluded(List<ProductRepresentation> excludedProductTypes) {
        addProductToOrderScreen.setExcluded(excludedProductTypes);
    }

    /**
     * Vrati uzivatelem vybrany Produkt.
     * @return Produkt
     */
    public ProductRepresentation getSelected() {
        return addProductToOrderScreen.getSelected();
    }

    @Override
    public void follow() {
        addProductToOrderScreen.follow();
    }

    @Override
    public void pass(UI source, PassResult result) {

    }
}
