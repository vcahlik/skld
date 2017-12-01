package cz.cvut.fit.si1.skld.client.resources;

public enum FXMLFragment {
    LOGIN_SCREEN, MAIN_MENU_SCREEN, SESSION_BAR_FRAGMENT;

    public String getFXMLFilePath() {
        String prefix = "/fxml/";
        String fileName;

        switch (this) {
            case LOGIN_SCREEN:
                fileName = "LoginScreen.fxml";
                break;
            case MAIN_MENU_SCREEN:
                fileName = "MainMenuScreen.fxml";
                break;
            case SESSION_BAR_FRAGMENT:
                fileName = "SessionBarFragment.fxml";
                break;
            default:
                throw new RuntimeException("FXML file path not added");
        }

        return prefix + fileName;
    }
}
