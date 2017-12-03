package cz.cvut.fit.si1.skld.client.components.add_product_type_screen;

import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.domain.ProductType;

public class AddProductTypeScreen extends Screen {
    private AddProductTypeScreenHandler handler;

    private EditProductTypeFragment editProductTypeFragment;

    public AddProductTypeScreen(Passable parent) {
        super(parent);
        setEditProductTypeFragment(new EditProductTypeFragment(this));
    }

    @Override
    public Handler makeHandler() {
        this.handler = new AddProductTypeScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        this.editProductTypeFragment = editProductTypeFragment;
        handler.setEditProductTypeFragment(editProductTypeFragment);
    }

    public void addProductType() {
        ProductType newProductType = editProductTypeFragment.getEdited();
        System.out.println("New product: id: " + newProductType.getId() + ", name: " + newProductType.getName() + " -> SERVER");
        getSource().pass(this, PassResult.DONE);
    }
}