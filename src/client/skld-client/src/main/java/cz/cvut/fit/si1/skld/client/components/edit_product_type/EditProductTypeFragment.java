package cz.cvut.fit.si1.skld.client.components.edit_product_type;

import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.Notifyable;
import javafx.scene.control.Alert;

public class EditProductTypeFragment extends Fragment {
    private EditProductTypeFragmentHandler handler;

    private ProductRepresentation editingProductType;
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
        setEditingProductType(new ProductRepresentation());
        this.disabled = false;
        handler.refresh();
    }

    public ProductRepresentation getEdited() throws NumberFormatException {
        try {
            editingProductType.setId(Long.parseLong(handler.getID()));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.show();
            throw e;
        }
        editingProductType.setName(handler.getName());
        return editingProductType;
    }

    protected ProductRepresentation getEditingProductType() {
        return editingProductType;
    }

    public void setEditingProductType(ProductRepresentation editingProductType) {
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
