package cz.cvut.fit.si1.skld.client.components.add_product_type_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.PassResult;
import cz.cvut.fit.si1.skld.client.Passable;
import cz.cvut.fit.si1.skld.client.Screen;
import cz.cvut.fit.si1.skld.client.components.edit_product_type.EditProductTypeFragment;
import cz.cvut.fit.si1.skld.client.util.exceptions.InputErrorException;

import java.io.IOException;

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
        ProductRepresentation newProductType = new ProductRepresentation();

        try {
            newProductType.setId(editProductTypeFragment.getEditedId());
        } catch (InputErrorException e) {
            return;
        }
        newProductType.setName(editProductTypeFragment.getEditedName());

        try {
            getApp().getHttpClient().createProduct(newProductType);
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        getSource().pass(this, PassResult.DONE);
    }
}
