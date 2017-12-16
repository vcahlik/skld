package cz.cvut.fit.project.skld.gui.resources;

/**
 * Shromazduje texty pouzivane v kodu aplikace. Vyjimkou jsou zpravidla texty ve FXML souborech, ty jsou v nich umisteny samostatne (hard-coded).
 */
public class Texts {
    /**
     * Texty souvisejici s okny
     */
    public static class Windows {
        public static final String MAIN_WINDOW_TITLE = "SKLD IS";
        public static final String ADD_PRODUCT_WINDOW_TITLE = "Výběr produktu";
    }

    /**
     * Texty souvisejici s alerty (pop-up okny)
     */
    public static class Alerts {
        public static final String CONNECTION_ERROR_ALERT_TITLE = "Chyba spojení";
        public static final String CONNECTION_ERROR_ALERT_TEXT = "Chyba v komunikaci se serverem.";

        public static final String ID_NOT_NUMBER_ERROR_ALERT_TITLE = "Chyba formátu";
        public static final String ID_NOT_NUMBER_ERROR_ALERT_TEXT = "ID musí být celé číslo.";

        public static final String ID_ALREADY_EXISTS_ERROR_ALERT_TITLE = "ID již existuje";
        public static final String ID_ALREADY_EXISTS_ERROR_ALERT_TEXT = "Položka se stejným ID již existuje.";

        public static final String PRODUCT_QUANTITY_NOT_NUMBER_ERROR_ALERT_TITLE = "Chyba formátu";
        public static final String PRODUCT_QUANTITY_NOT_NUMBER_ERROR_ALERT_TEXT = "Množství produktu musí být celé číslo.";
    }

    /**
     * Texty, ktere jsou vypsany na konzoli
     */
    public static class ConsoleOutput {
        public static final String USAGE_HINT = "Pouziti: java -jar gui.jar [configfile]";
        public static final String NO_CONFIG_FILE_SPECIFIED_MESSAGE = "Nebyl specifikovan soubor s konfiguraci, bude pouzito defaultni nastaveni.";
    }
}
