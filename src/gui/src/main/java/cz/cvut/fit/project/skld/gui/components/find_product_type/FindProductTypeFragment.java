package cz.cvut.fit.project.skld.gui.components.find_product_type;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Fragment pro vyhledani existujiciho typu produktu.
 */
public class FindProductTypeFragment extends Fragment {
    private FindProductTypeFragmentHandler handler;

    private List<ProductRepresentation> productTypes;
    private ProductRepresentation selectedProductType;

    private List<ProductRepresentation> excludedProductTypes;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public FindProductTypeFragment(Notifyable parent) {
        super(parent);
        productTypes = new ArrayList<>();
        excludedProductTypes = new ArrayList<>();
        refresh();
    }

    /**
     * Zadane produkty nebudou zobrazeny v seznamu.
     * @param excludedProductTypes Vynechavane zobrazeny
     */
    public void setExcluded(List<ProductRepresentation> excludedProductTypes) {
        this.excludedProductTypes = excludedProductTypes;
        refresh();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new FindProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zjisti, zda uzivatel oznacil nejaky produkt ze seznamu
     * @return true pokud existuje oznaceny produkt
     */
    public boolean isAnyProductTypeSelected() {
        return getSelected() != null;
    }

    /**
     * Vrati uzivatelem oznaceny produkt.
     * @return Oznaceny produkt
     */
    public ProductRepresentation getSelected() {
        return selectedProductType;
    }

    /**
     * Oznaci typ produktu jako oznaceny uzivatelem.
     * @param selectedProductType Oznaceny typ produktu
     */
    protected void setSelectedProductType(ProductRepresentation selectedProductType) {
        if (this.selectedProductType != selectedProductType) {
            this.selectedProductType = selectedProductType;
            getParent().notify(this, NotifyType.CHANGE);
        }
    }

    /**
     * Nacte seznam vsech typu produktu a zobrazi jej (s vynechanim typu oznacenych jako excluded).
     */
    public void refresh() {
        try {
            productTypes = getApp().getClient().getProducts();
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }
        productTypes.removeAll(excludedProductTypes);
        handler.setProductTypes(productTypes);

        handler.clearSelection();
    }
}
