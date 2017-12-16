package cz.cvut.fit.project.skld.gui.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static Config instance;

    private static String configFilePath = "";
    private Properties properties;

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

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getServerUrl() {
        return properties.getProperty(PropertyNames.SERVER_URL, DefaultConfig.SERVER_URL);
    }

    public static void setConfigFilePath(String configFilePath) {
        Config.configFilePath = configFilePath;
    }

    private static class PropertyNames {
        private static final String SERVER_URL = "serverUrl";
    }

    private static class DefaultConfig {
        private static final String SERVER_URL = "http://localhost:8080";
    }

}
