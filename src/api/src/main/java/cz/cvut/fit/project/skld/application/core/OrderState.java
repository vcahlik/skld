package cz.cvut.fit.project.skld.application.core;


/**
 * Reprezentuje stav objednavky z domenoveho modelu.
 */
public enum OrderState {
    /**
     * Informace o objednavce byly predany skladu provoznim oddelenim a ceka se na jeji zpracovani.
     */
    OPEN,
    /**
     * Objednavka byla zpracovana (naskladnena/odeslana).
     */
    CLOSED,
    /**
     * Pri zpracovani objednavky doslo k problemu a byla zrusena.
     */
    REFUSED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
