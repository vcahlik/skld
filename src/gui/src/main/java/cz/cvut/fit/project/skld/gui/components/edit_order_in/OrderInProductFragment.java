package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

/**
 * Fragment reprezentujici produkt obsazeny v aktualne editovane logisticke objednavce.
 */
public class OrderInProductFragment extends Fragment {
    private OrderInProductFragmentHandler handler;

    private ProductRepresentation productRepresentation;
    private boolean deleteRequested;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     * @param productRepresentation Typ produktu
     */
    public OrderInProductFragment(Notifyable parent, ProductRepresentation productRepresentation) {
        super(parent);
        deleteRequested = false;
        setProductRepresentation(productRepresentation);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new OrderInProductFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Zjisti, zda uzivatel odstranil produkt ze seznamu.
     * @return
     */
    public boolean isDeleteRequested() {
        return deleteRequested;
    }

    /**
     * Zavolano pri pozadavku uzivatele o odstraneni produkt ze seznamu.
     */
    protected void requestDelete() {
        this.deleteRequested = true;
        getParent().notify(this, NotifyType.CHANGE);
    }

    /**
     * Vraci editovany produkt s jeho zadanym mnozstvim.
     * @return Produkt
     * @throws InvalidInputException Pri nespravnem mnozstvi (neni cele cislo)
     */
    public ProductRepresentation getEditedProductRepresentation() throws InvalidInputException {
        try {
            productRepresentation.setQuantity(Long.parseLong(handler.getQuantity()));
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.PRODUCT_QUANTITY_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.PRODUCT_QUANTITY_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
        }

        return productRepresentation;
    }

    /**
     * Vraci puvodni editovany produkt
     * @return Produkt
     */
    protected ProductRepresentation getProductRepresentation() {
        return productRepresentation;
    }

    private void setProductRepresentation(ProductRepresentation productRepresentation) {
        this.productRepresentation = productRepresentation;
        handler.refresh();
    }

}
