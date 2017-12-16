package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.add_product_to_order.AddProductToOrderWindow;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Fragment pro editaci (existujici/nove) logisticke objednavky.
 */
public class EditOrderInFragment extends Fragment {
    private EditOrderInFragmentHandler handler;

    private List<OrderInProductFragment> orderInProductFragments;
    private AddProductToOrderWindow addProductToOrderWindow;

    private boolean idEditEnabled;
    private boolean enabled;
    private boolean filled;

    private long id;
    private Date deliveryDate;
    private String supplierName;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     * @param idEditEnabled true umozni editaci ID typu produktu
     */
    public EditOrderInFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);
        orderInProductFragments = new ArrayList<>();
        this.idEditEnabled = idEditEnabled;
        reset();
    }

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public EditOrderInFragment(Notifyable parent) {
        this(parent, true);
    }

    /**
     * Vrati objekt do pocatecniho stavu.
     */
    public void reset() {
        this.filled = false;
        this.id = 0;
        this.deliveryDate = new Date();
        this.supplierName = "";
        orderInProductFragments.clear();
        setEnabled(true);
    }

    /**
     * Vyplni polozky podle hodnot dane logisticke objednavky
     * @param orderInRepresentation Logisticka objednavka
     */
    public void fill(OrderInRepresentation orderInRepresentation) {
        this.filled = true;
        setId(orderInRepresentation.getId());
        setDeliveryDate(orderInRepresentation.getDeliveryDate());
        setSupplierName(orderInRepresentation.getSupplierName());
        orderInProductFragments.clear();
        for (ProductRepresentation productRepresentation : orderInRepresentation.getProducts()) {
            orderInProductFragments.add(new OrderInProductFragment(this, productRepresentation));
        }

        handler.reset();
    }

    /**
     * Umozni/znemozni editaci policek formulare.
     * @param enabled true editace bude umoznena
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        handler.reset();
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == addProductToOrderWindow && notifyType == NotifyType.CHANGE) {
            onProductAdded();
        } else if (notifyType == NotifyType.CHANGE) {
            removeDeletedProducts();
        }
    }

    private void removeDeletedProducts() {
        List<OrderInProductFragment> toRemove = new ArrayList<>();
        for (OrderInProductFragment orderInProductFragment : orderInProductFragments) {
            if (orderInProductFragment.isDeleteRequested()) {
                toRemove.add(orderInProductFragment);
            }
        }

        orderInProductFragments.removeAll(toRemove);
        for (OrderInProductFragment orderInProductFragment : toRemove) {
            handler.removeOrderInProductFragment(orderInProductFragment);
        }
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditOrderInFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    private List<ProductRepresentation> getProducts() {
        List<ProductRepresentation> products = new ArrayList<>();
        for (OrderInProductFragment fragment : orderInProductFragments) {
            products.add(fragment.getProductRepresentation());
        }
        return products;
    }

    /**
     * Prida typ produktu do seznamu zobrazenych typu produktu.
     */
    protected void addProduct() {
        addProductToOrderWindow = new AddProductToOrderWindow(this);
        addProductToOrderWindow.setExcluded(getProducts());
        addProductToOrderWindow.follow();
    }

    private void onProductAdded() {
        ProductRepresentation product = addProductToOrderWindow.getSelected();
        addProductToOrderWindow.close();

        product.setQuantity((long)1);

        addOrderInProductFragment(new OrderInProductFragment(this, product));
    }

    private void addOrderInProductFragment(OrderInProductFragment fragment) {
        orderInProductFragments.add(fragment);
        handler.addOrderInProductFragment(fragment);
    }

    /**
     * Pokusi se vratit uzivatelem zadane ID, pri nespravnem formatu zobrazi alert a vyhodi vyjimku.
     * @return Zadane ID
     * @throws InvalidInputException ID neni cele cislo
     */
    public long getEditedId() throws InvalidInputException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
        }
    }

    /**
     * Vraci puvodni ID editovane logisticke objednavky.
     * @return ID
     */
    protected Long getId() {
        return id;
    }

    /**
     * Nastavi puvodni ID logisticke objednavky.
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Vraci uzivatelem zadane datum ocekavaneho doruceni logisticke objednavky.
     * @return Datum ocekavaneho doruceni
     */
    public Date getEditedDeliveryDate() {
        LocalDate localDate = handler.getDeliveryDate();
        if (localDate == null) {
            return null;
        }

        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Vraci ocekavane datum doruceni puvodni editovane logisticke objednavky.
     * @return Ocekavane datum doruceni
     */
    protected Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Nastavi predzobrazene datum doruceni puvodni editovane logisticke objednavky.
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Vraci uzivatelem zadany nazev dodavatele logisticke objednavky.
     * @return Nazev dodavatele
     */
    public String getEditedSupplierName() {
        return handler.getSupplierName();
    }

    /**
     * Vraci puvodni jmeno dodavatele editovane objednavky.
     * @return Jmeno dodavatele
     */
    protected String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<ProductRepresentation> getEditedProducts() throws InvalidInputException {
        List<ProductRepresentation> products = new ArrayList<>();
        for (OrderInProductFragment fragment : orderInProductFragments) {
            products.add(fragment.getEditedProductRepresentation());
        }
        return products;
    }

    /**
     * Zjistuje zda je umoznena editace ID typu produktu.
     * @return true editace umoznena
     */
    protected boolean isIdEditEnabled() {
        return idEditEnabled;
    }

    /**
     * Zjistuje zda je umoznena editace.
     * @return true editace umoznena
     */
    protected boolean isEnabled() {
        return enabled;
    }

    /**
     * Vraci fragmenty veskerych produktu obsazenych v aktualne editovane logisticke objednavce.
     * @return
     */
    protected List<OrderInProductFragment> getOrderInProductFragments() {
        return orderInProductFragments;
    }

    /**
     * Zjistuje zda byly polozky formulare naplneny nejakym existujicim typem produktu (pri jeho zmene).
     * @return true formular byl naplnen
     */
    public boolean isFilled() {
        return filled;
    }
}
