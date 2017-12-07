package cz.cvut.fit.si1.skld.client.components.change_product_type_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.representations.ProductEdit;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.components.find_product_type.FindProductTypeFragment;

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
        editProductTypeFragment.setDisabled(!editEnabled);
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

        ProductEdit edit = new ProductEdit();
        edit.setName(editProductTypeFragment.getEditedName());

        try {
            getApp().getHttpClient().changeProduct(selected.getId(), edit);
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
