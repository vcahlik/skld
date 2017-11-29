package cz.cvut.fit.si1.skld.client.view;

public enum AppScreen {
    LOGIN, MAIN_MENU;

    public static String getFXMLFileName(AppScreen screen) {
        switch (screen) {
            case LOGIN:
                return "/fxml/LoginScreen.fxml";
            case MAIN_MENU:
                return "/fxml/MainMenuScreen.fxml";
            default:
                throw new IllegalArgumentException("Unknown screen");
        }
    }
}
