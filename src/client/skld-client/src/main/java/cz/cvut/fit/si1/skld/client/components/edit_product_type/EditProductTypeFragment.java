package cz.cvut.fit.si1.skld.client.components.edit_product_type;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Notifyable;
import cz.cvut.fit.si1.skld.client.domain.ProductType;

public class EditProductTypeFragment extends Fragment {
    private EditProductTypeFragmentHandler handler;

    private ProductType editingProductType;
    private boolean disabled;
    private boolean idEditEnabled;

    public EditProductTypeFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);

        this.idEditEnabled = idEditEnabled;
        reset();
    }

    public EditProductTypeFragment(Notifyable parent) {
        this(parent, true);
    }

    public void reset() {
        setEditingProductType(new ProductType());
        this.disabled = false;
        handler.refresh();
    }

    public ProductType getEdited() {
        editingProductType.setId(handler.getID());
        editingProductType.setName(handler.getName());
        return editingProductType;
    }

    protected ProductType getEditingProductType() {
        return editingProductType;
    }

    public void setEditingProductType(ProductType editingProductType) {
        this.editingProductType = editingProductType;
        handler.refresh();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public boolean isIdEditEnabled() {
        return idEditEnabled;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
        handler.refresh();
    }
}
