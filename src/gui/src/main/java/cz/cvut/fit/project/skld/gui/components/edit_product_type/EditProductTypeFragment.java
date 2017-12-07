package cz.cvut.fit.project.skld.gui.components.edit_product_type;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InputErrorException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

public class EditProductTypeFragment extends Fragment {
    private EditProductTypeFragmentHandler handler;

    private long id;
    private String name;
    private boolean idSet;

    private boolean disabled;
    private boolean idEditEnabled;

    public EditProductTypeFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);

        this.idSet = false;
        this.idEditEnabled = idEditEnabled;
        reset();
    }

    public EditProductTypeFragment(Notifyable parent) {
        this(parent, true);
    }

    public void reset() {
        id = 0;
        name = "";
        idSet = false;
        this.disabled = false;
        handler.refresh();
    }

    public void fill(ProductRepresentation product) {
        setId(product.getId());
        setName(product.getName());
        handler.refresh();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public long getEditedId() throws InputErrorException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InputErrorException();
        }
    }

    public String getEditedName() {
        return handler.getName();
    }

    protected long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
        idSet = true;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected boolean isIdSet() {
        return idSet;
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
