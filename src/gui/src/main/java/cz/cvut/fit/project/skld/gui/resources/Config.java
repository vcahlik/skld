package cz.cvut.fit.project.skld.gui.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Shromazduje veskerou konfiguraci aplikace.
 * Kazde nastaveni muze byt nastavitelne bud pouze zde ve tride jako konstantni hodnota, nebo muze byt umoznena jeho konfigurace uzivatelem v .properties souboru aplikace.
 * Singleton trida. Pri vytvoreni instance se pokusi nacist uzivatelem specifikovany soubor s konfiguraci, jinak budou pouzity defaultni hodnoty.
 */
public class Config {
    private static Config instance;

    private static String configFilePath = "";
    private Properties properties;

    /**
     * Singleton konstruktor. Pokusi se nacist uzivatelem specifikovany soubor s konfiguraci.
     */
    protected Config() {
        properties = new Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(configFilePath);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            // Config file not present or IO error, will use default property values
            return;
        }
    }

    /**
     * Vraci (singleton) instanci
     * @return Instance
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Nastavi cestu k souboru s konfiguraci. Musi byt nastaveno pred vytvorenim singleton instance.
     * @param configFilePath Cesta k souboru s konfiguraci
     */
    public static void setConfigFilePath(String configFilePath) {
        Config.configFilePath = configFilePath;
    }

    /**
     * Vraci URL serveru SKLD systemu. Konfigurovatelne uzivatelem v config souboru.
     * @return URL serveru
     */
    public String getServerUrl() {
        return properties.getProperty(PropertyNames.SERVER_URL, DefaultConfig.SERVER_URL);
    }

    /**
     * Obsahuje nazvy atributu v souboru s konfiguraci.
     */
    private static class PropertyNames {
        private static final String SERVER_URL = "serverUrl";
    }

    /**
     * Obsahuje defaultni hodnoty atributu v souboru s konfiguraci. Jsou pouzity, pokud uzivatel nezada prislusnou hodnotu do konfigurace.
     */
    private static class DefaultConfig {
        private static final String SERVER_URL = "http://localhost:8080";
    }

}
