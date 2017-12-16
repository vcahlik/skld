package cz.cvut.fit.project.skld.gui.components.edit_product_type;

import cz.cvut.fit.project.skld.gui.FXMLFragmentType;
import cz.cvut.fit.project.skld.gui.Handler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Handler pro EditProductTypeFragment.
 */
public class EditProductTypeFragmentHandler extends Handler {
    private EditProductTypeFragment owner;

    @FXML
    private TextField inputID;

    @FXML
    private TextField inputName;

    /**
     * Konstruktor.
     */
    public EditProductTypeFragmentHandler() {
        super(FXMLFragmentType.EDIT_PRODUCT_TYPE_FRAGMENT);
    }

    /**
     * Pripoji k handleru prislusny fragment.
     * @param owner Fragment
     */
    public void setOwner(EditProductTypeFragment owner) {
        this.owner = owner;
    }

    /**
     * Vraci objekt do pocatecniho stavu.
     */
    public void reset() {
        if (owner.isFilled()) {
            inputID.setText(Long.toString(owner.getId()));
        } else {
            inputID.setText("");
        }
        inputName.setText(owner.getName());

        inputID.setDisable(!owner.isEnabled() || !owner.isIdEditEnabled());
        inputName.setDisable(!owner.isEnabled());
    }

    /**
     * Vraci uzivatelem vyplnene ID typu produktu.
     * @return ID typu produktu
     */
    public String getID() {
        return inputID.getText();
    }

    /**
     * Vraci uzivatelem zadane jmeno typu produktu.
     * @return Jmeno typu produktu
     */
    public String getName() {
        return inputName.getText();
    }
}
