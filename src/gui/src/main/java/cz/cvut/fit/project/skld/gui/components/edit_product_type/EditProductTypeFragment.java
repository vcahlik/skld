package cz.cvut.fit.project.skld.gui.components.edit_product_type;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.Notifyable;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.gui.util.exceptions.InvalidInputException;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;

/**
 * Fragment pro editaci (noveho/existujiciho) typu produktu.
 */
public class EditProductTypeFragment extends Fragment {
    private EditProductTypeFragmentHandler handler;

    private boolean enabled;
    private boolean idEditEnabled;
    private boolean filled;

    private long id;
    private String name;

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     * @param idEditEnabled true umozni editaci ID typu produktu
     */
    public EditProductTypeFragment(Notifyable parent, boolean idEditEnabled) {
        super(parent);
        this.idEditEnabled = idEditEnabled;
        reset();
    }

    /**
     * Konstruktor.
     * @param parent Rodicovsky objekt
     */
    public EditProductTypeFragment(Notifyable parent) {
        this(parent, true);
    }

    /**
     * Vrati objekt do pocatecniho stavu.
     */
    public void reset() {
        this.filled = false;
        setId(0);
        setName("");
        setEnabled(true);
        handler.reset();
    }

    /**
     * Vyplni polozky podle hodnot daneho typu produktu
     * @param product Typ produktu
     */
    public void fill(ProductRepresentation product) {
        this.filled = true;
        setId(product.getId());
        setName(product.getName());
        handler.reset();
    }

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new EditProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Pokusi se vratit uzivatelem zadane ID, pri nespravnem formatu zobrazi alert a vyhodi vyjimku.
     * @return Zadane ID
     * @throws InvalidInputException ID neni cele cislo
     */
    public long getEditedId() throws InvalidInputException {
        try {
            return Long.parseLong(handler.getID());
        } catch (NumberFormatException e) {
            FXUtil.displayAlert(Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TITLE, Texts.Alerts.ID_NOT_NUMBER_ERROR_ALERT_TEXT);
            throw new InvalidInputException();
        }
    }

    /**
     * Vraci uzivatelem zadane jmeno typu produktu.
     * @return Jmeno typu produktu
     */
    public String getEditedName() {
        return handler.getName();
    }

    /**
     * Vraci puvodni ID editovaneho typu produktu.
     * @return ID
     */
    protected long getId() {
        return id;
    }

    /**
     * Nastavi puvodni ID editovaneho typu produktu.
     * @param id ID
     */
    protected void setId(long id) {
        this.id = id;
    }

    /**
     * Vraci puvodni jmeno editovaneho typu produktu.
     * @return Jmeno typu produktu
     */
    protected String getName() {
        return name;
    }

    /**
     * nastavi puvodni jmeno editovaneho typu produktu.
     * @param name Jmeno typu produktu
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Zjistuje zda byly polozky formulare naplneny nejakym existujicim typem produktu (pri jeho zmene).
     * @return true formular byl naplnen
     */
    protected boolean isFilled() {
        return filled;
    }

    /**
     * Zjistuje zda je umoznena editace ID typu produktu.
     * @return true editace umoznena
     */
    public boolean isIdEditEnabled() {
        return idEditEnabled;
    }

    /**
     * Zjistuje zda je umoznena editace.
     * @return true editace umoznena
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Umozni/znemozni editaci policek formulare.
     * @param enabled true editace bude umoznena
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        handler.reset();
    }
}
