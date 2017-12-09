package cz.cvut.fit.project.skld.gui.components.edit_product_type;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

public class EditProductTypeFragment extends Fragment {
    private EditProductTypeFragmentHandler handler;

    private boolean enabled;
    private boolean idEditEnabled;
    private boolean filled;

    private long id;
    private String name;

    public EditProductTypeFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);
        this.idEditEnabled = idEditEnabled;
        reset();
    }

    public EditProductTypeFragment(Notifyable parent) {
        this(parent, true);
    }

    public void reset() {
        this.filled = false;
        setId(0);
        setName("");
        setEnabled(true);
        handler.reset();
    }

    public void fill(ProductRepresentation product) {
        this.filled = true;
        setId(product.getId());
        setName(product.getName());
        handler.reset();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new EditProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public long getEditedId() throws InvalidInputException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
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
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected boolean isFilled() {
        return filled;
    }

    public boolean isIdEditEnabled() {
        return idEditEnabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        handler.reset();
    }
}
