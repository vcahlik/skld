package cz.cvut.fit.project.skld.gui.components.edit_order_in;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.add_product_to_order.AddProductToOrderWindow;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.util.ArrayList;
import java.util.List;

public class EditOrderInFragment extends Fragment {
    private EditOrderInFragmentHandler handler;

    private List<OrderInProductFragment> orderInProductFragments;
    private AddProductToOrderWindow addProductToOrderWindow;

    public EditOrderInFragment(Notifyable parent) {
        super(parent);
        orderInProductFragments = new ArrayList<>();
    }

    public long getEditedId() throws InvalidInputException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
        }
    }

    public String getEditedSupplierName() {
        return handler.getSupplierName();
    }

    public List<ProductRepresentation> getEditedProducts() throws InvalidInputException {
        List<ProductRepresentation> products = new ArrayList<>();
        for (OrderInProductFragment fragment : orderInProductFragments) {
            products.add(fragment.getEditedProductRepresentation());
        }
        return products;
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

        OrderInProductFragment fragment = new OrderInProductFragment(this, product);
        orderInProductFragments.add(fragment);
        handler.addOrderInProductFragment(fragment);
    }
}
