package cz.cvut.fit.project.skld.gui.components.change_product_type_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.project.skld.gui.components.edit_product_type.EditProductTypeFragment;

import java.io.IOException;

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
        editProductTypeFragment.setEnabled(editEnabled);
        this.editEnabled = editEnabled;
        handler.refresh();
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (source == findProductTypeFragment && notifyType == NotifyType.CHANGE) {
            ProductRepresentation editingProductType = findProductTypeFragment.getSelected();
            if (editingProductType != null) {
                changeEditingProductType(editingProductType);
            }
        }
    }

    private void changeEditingProductType(ProductRepresentation product) {
        editProductTypeFragment.fill(product);
        setEditEnabled(true);
    }

    public void submitChangedProductType() {
        ProductRepresentation selected = findProductTypeFragment.getSelected();

        ProductChange change = new ProductChange();
        change.setId(selected.getId());
        change.setName(editProductTypeFragment.getEditedName());

        try {
            getApp().getClient().changeProduct(change);
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

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
