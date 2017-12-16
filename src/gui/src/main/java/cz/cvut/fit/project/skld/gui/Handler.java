package cz.cvut.fit.project.skld.gui;

import cz.cvut.fit.project.skld.gui.resources.Files;

/**
 * Trida, ktera tesne komunikuje s GUI objekty knihovny JavaFX, zpracovava uzivatelsky vstup a odesila jej sve komponente.
 * Kazdy handler (v terminologii JavaFX jde o controller) ma vlastni komponentu.
 * Schema GUI je reprezentovano v JavaFX FXML souboru.
 */
public abstract class Handler {
    private FXMLFragmentType fxmlFragmentType;

    /**
     * Konstruktor
     * @param fxmlFragmentType Typ fragmentu
     */
    public Handler(FXMLFragmentType fxmlFragmentType) {
        this.fxmlFragmentType = fxmlFragmentType;
    }

    /**
     * Vraci cestu k FXML souboru
     * @return Cesta k FXML souboru
     */
    public String getFXMLFilePath() {
        return Files.getFXMLFilePath(fxmlFragmentType);
    }
}
