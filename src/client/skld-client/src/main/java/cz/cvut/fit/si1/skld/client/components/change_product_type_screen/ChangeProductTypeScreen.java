package cz.cvut.fit.si1.skld.client.components.change_product_type_screen;

import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.si1.skld.client.domain.ProductType;

public class ChangeProductTypeScreen extends Screen {
    private ChangeProductTypeScreenHandler handler;

    private FindProductTypeFragment findProductTypeFragment;
    private EditProductTypeFragment editProductTypeFragment;

    private boolean editEnabled;

    public ChangeProductTypeScreen(Passable source) {
        super(source);

        setFindProductTypeFragment(new FindProductTypeFragment(this));
        setEditProductTypeFragment(new EditProductTypeFragment(this, false));
        reset();
    }

    public void reset() {
        findProductTypeFragment.refresh();
        editProductTypeFragment.reset();

        setEditEnabled(false);
    }

    public boolean isEditEnabled() {
        return editEnabled;
    }

    public void setEditEnabled(boolean editEnabled) {
        editProductTypeFragment.setDisabled(!editEnabled);
        this.editEnabled = editEnabled;
        handler.refresh();
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == findProductTypeFragment && notifyType == NotifyType.CHANGE) {
            ProductType editingProductType = findProductTypeFragment.getSelectedProductType();
            if (editingProductType != null) {
                changeEditingProductType(editingProductType);
            }
        }
    }

    private void changeEditingProductType(ProductType editingProductType) {
        editProductTypeFragment.setEditingProductType(editingProductType);
        setEditEnabled(true);
    }

    public void submitChangedProductType() {
        ProductType changedProductType = editProductTypeFragment.getEdited();
        System.out.println("Change product: id: " + changedProductType.getId() + ", name: " + changedProductType.getName() + " -> SERVER");
        reset();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new ChangeProductTypeScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    public void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        this.findProductTypeFragment = findProductTypeFragment;
        handler.setFindProductTypeFragment(findProductTypeFragment);
    }

    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        this.editProductTypeFragment = editProductTypeFragment;
        handler.setEditProductTypeFragment(editProductTypeFragment);
    }
}
