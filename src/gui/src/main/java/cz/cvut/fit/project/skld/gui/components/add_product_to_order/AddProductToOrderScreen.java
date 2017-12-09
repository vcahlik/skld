package cz.cvut.fit.project.skld.gui.components.add_product_to_order;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

import java.util.List;

public class AddProductToOrderScreen extends Screen {
    private AddProductToOrderScreenHandler handler;

    private FindProductTypeFragment findProductTypeFragment;

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

    public void setExcluded(List<ProductRepresentation> excludedProductTypes) {
        findProductTypeFragment.setExcluded(excludedProductTypes);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new AddProductToOrderScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    private void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        this.findProductTypeFragment = findProductTypeFragment;
        handler.setFindProductTypeFragment(findProductTypeFragment);
    }

    public ProductRepresentation getSelected() {
        return findProductTypeFragment.getSelected();
    }

    protected void onSubmit() {
        getSource().notify(this, NotifyType.CHANGE);
    }

    protected boolean isSelectEnabled() {
        return findProductTypeFragment.isSelected();
    }
}
