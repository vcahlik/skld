package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.add_product_to_order.AddProductToOrderWindow;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.OrderInRepresentation;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.util.ArrayList;
import java.util.List;

public class EditOrderInFragment extends Fragment {
    private EditOrderInFragmentHandler handler;

    private List<OrderInProductFragment> orderInProductFragments;
    private AddProductToOrderWindow addProductToOrderWindow;

    private boolean idEditEnabled;
    private boolean enabled;
    private boolean filled;

    private long id;
    private String supplierName;

    public EditOrderInFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);
        orderInProductFragments = new ArrayList<>();
        this.idEditEnabled = idEditEnabled;
        this.filled = false;
        this.id = 0;
        this.supplierName = "";
        setEnabled(true);
    }

    public EditOrderInFragment(Notifyable parent) {
        this(parent, true);
    }

    public void fill(OrderInRepresentation orderInRepresentation) {
        this.filled = true;
        setId(orderInRepresentation.getId());
        setSupplierName(orderInRepresentation.getSupplierName());
        orderInProductFragments.clear();
        for (ProductRepresentation productRepresentation : orderInRepresentation.getProducts()) {
            orderInProductFragments.add(new OrderInProductFragment(this, productRepresentation));
        }

        handler.reset();
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        handler.reset();
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == addProductToOrderWindow && notifyType == NotifyType.CHANGE) {
            productAdded();
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

    protected void addProduct() {
        addProductToOrderWindow = new AddProductToOrderWindow(this);
        addProductToOrderWindow.setExcluded(getProducts());
        addProductToOrderWindow.follow();
    }

    private void productAdded() {
        ProductRepresentation product = addProductToOrderWindow.getSelected();
        addProductToOrderWindow.close();

        product.setQuantity((long)1);

        addOrderInProductFragment(new OrderInProductFragment(this, product));
    }

    private void addOrderInProductFragment(OrderInProductFragment fragment) {
        orderInProductFragments.add(fragment);
        handler.addOrderInProductFragment(fragment);
    }

    public long getEditedId() throws InvalidInputException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
        }
    }

    protected Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEditedSupplierName() {
        return handler.getSupplierName();
    }

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

    protected boolean isIdEditEnabled() {
        return idEditEnabled;
    }

    protected boolean isEnabled() {
        return enabled;
    }

    protected List<OrderInProductFragment> getOrderInProductFragments() {
        return orderInProductFragments;
    }

    public boolean isFilled() {
        return filled;
    }
}
