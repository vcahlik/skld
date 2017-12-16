package cz.cvut.fit.project.skld.gui.components.add_product_to_order;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.util.List;

/**
 * Obrazovka pro pro vybrani typu produktu k zarazeni do objednavky.
 */
public class AddProductToOrderScreen extends Screen {
    private AddProductToOrderScreenHandler handler;

    private FindProductTypeFragment findProductTypeFragment;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public AddProductToOrderScreen(Passable source) {
        super(source);

        setFindProductTypeFragment(new FindProductTypeFragment(this));
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == findProductTypeFragment && notifyType == NotifyType.CHANGE) {
            handler.refresh();
        }
    }

    /**
     * Zadane typy produktu nebudou zobrazeny v seznamu.
     * @param excludedProductTypes Exkludovane typy
     */
    public void setExcluded(List<ProductRepresentation> excludedProductTypes) {
        findProductTypeFragment.setExcluded(excludedProductTypes);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new AddProductToOrderScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Nastavi FindProductTypeFragment pro vyhledani typu produktu.
     * @param findProductTypeFragment FindProductTypeFragment
     */
    private void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        this.findProductTypeFragment = findProductTypeFragment;
        handler.setFindProductTypeFragment(findProductTypeFragment);
    }

    /**
     * Vrati uzivatelem vybrany Produkt.
     * @return Produkt
     */
    public ProductRepresentation getSelected() {
        return findProductTypeFragment.getSelected();
    }

    /**
     * Zavolano pri potvrzeni uzivatelem.
     */
    protected void onSubmit() {
        getSource().notify(this, NotifyType.CHANGE);
    }

    /**
     * Zjisti zda je uzivateli umozneno vybrat nejaky produkt.
     * @return true vybrani je umozneno
     */
    protected boolean isSelectEnabled() {
        return findProductTypeFragment.isAnyProductTypeSelected();
    }
}
