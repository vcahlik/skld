package cz.cvut.fit.project.skld.gui.components.change_product_type_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.find_product_type.FindProductTypeFragment;
import cz.cvut.fit.project.skld.representations.ProductChange;
import cz.cvut.fit.project.skld.representations.ProductRepresentation;
import cz.cvut.fit.project.skld.gui.components.edit_product_type.EditProductTypeFragment;

import java.io.IOException;

/**
 * Obrazovka pro zobrazeni existujicich typu produktu a jejich zmenu.
 */
public class ChangeProductTypeScreen extends Screen {
    private ChangeProductTypeScreenHandler handler;

    private FindProductTypeFragment findProductTypeFragment;
    private EditProductTypeFragment editProductTypeFragment;

    private boolean editEnabled;

    /**
     * Konstruktor.
     * @param source Rodicovsky objekt
     */
    public ChangeProductTypeScreen(Passable source) {
        super(source);

        setFindProductTypeFragment(new FindProductTypeFragment(this));
        setEditProductTypeFragment(new EditProductTypeFragment(this, false));
        reset();
    }

    /**
     * Vraci objekt do pocatecniho stavu.
     */
    public void reset() {
        findProductTypeFragment.refresh();
        editProductTypeFragment.reset();
        setEditEnabled(false);
    }

    /**
     * Zjisti, zda je uzivateli umoznena editace zobrazenych hodnot.
     * @return true editace umoznena
     */
    public boolean isEditEnabled() {
        return editEnabled;
    }

    /**
     * Nastavi, zda je uzivateli umoznena editace zobrazenych hodnot.
     * @param editEnabled true editace bude umoznena
     */
    public void setEditEnabled(boolean editEnabled) {
        editProductTypeFragment.setEnabled(editEnabled);
        this.editEnabled = editEnabled;
        handler.refresh();
    }

    /**
     * Zachyti a zpracuje notifikace o udalostech v dcerinnych objektech.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
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

    /**
     * Zavolano pri odeslani formulare uzivatelem. Odesle zmenu na server.
     */
    public void onSubmitChangedProductType() {
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

    /**
     * Factory metoda pro tvoreni handleru.
     * Smi byt zavolana behem zivota fragmentu pouze jednou.
     * Diky tomu muze mit kazdy fragment (implementovany zvlastni tridou dedenou z Fragment) vlastni handler (implementovany zvlastni tridou dedenou z Handler).
     * @return Nove vytvoreny handler
     */
    @Override
    public Handler makeHandler() {
        this.handler = new ChangeProductTypeScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    /**
     * Nastavi FindProductTypeFragment pro vyhledani typu produktu.
     * @param findProductTypeFragment FindProductTypeFragment
     */
    public void setFindProductTypeFragment(FindProductTypeFragment findProductTypeFragment) {
        this.findProductTypeFragment = findProductTypeFragment;
        handler.setFindProductTypeFragment(findProductTypeFragment);
    }

    /**
     * Nastavi EditProductTypeFragment pro editaci existujiciho typu produktu.
     * @param editProductTypeFragment EditProductTypeFragment
     */
    public void setEditProductTypeFragment(EditProductTypeFragment editProductTypeFragment) {
        this.editProductTypeFragment = editProductTypeFragment;
        handler.setEditProductTypeFragment(editProductTypeFragment);
    }
}
