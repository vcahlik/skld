package cz.cvut.fit.project.skld.gui.components.add_product_type_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.client.exceptions.BadRequestException;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.PassResult;
import cz.cvut.fit.project.skld.gui.Passable;
import cz.cvut.fit.project.skld.gui.Screen;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.gui.components.edit_product_type.EditProductTypeFragment;

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
        ProductChange change = new ProductChange();

        try {
            change.setId(editProductTypeFragment.getEditedId());
        } catch (InvalidInputException e) {
            return;
        }
        change.setName(editProductTypeFragment.getEditedName());

        try {
            getApp().getHttpClient().createProduct(change);
        } catch (BadRequestException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_ALREADY_EXISTS_ERROR_ALERT_TITLE, Texts.Alerts.ID_ALREADY_EXISTS_ERROR_ALERT_TEXT);
            return;
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        getSource().pass(this, PassResult.DONE);
    }
}
