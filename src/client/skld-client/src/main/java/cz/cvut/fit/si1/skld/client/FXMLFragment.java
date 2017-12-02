package cz.cvut.fit.si1.skld.client;

public enum FXMLFragment {
    LOGIN_SCREEN, MAIN_MENU_SCREEN, SESSION_BAR_FRAGMENT, NAVIGATOR_FRAME, NAVIGATOR_BAR_FRAGMENT, ADD_PRODUCT_TYPE_SCREEN, EDIT_PRODUCT_TYPE_FRAGMENT;

    public String getFXMLFilePath() {
        String fileName;

        switch (this) {
            case LOGIN_SCREEN:
                fileName = "/fxml/LoginScreen.fxml";
                break;
            case MAIN_MENU_SCREEN:
                fileName = "/fxml/MainMenuScreen.fxml";
                break;
            case SESSION_BAR_FRAGMENT:
                fileName = "/fxml/SessionBarFragment.fxml";
                break;
            case NAVIGATOR_FRAME:
                fileName = "/fxml/NavigatorFrame.fxml";
                break;
            case NAVIGATOR_BAR_FRAGMENT:
                fileName = "/fxml/NavigatorBarFragment.fxml";
                break;
            case ADD_PRODUCT_TYPE_SCREEN:
                fileName = "/fxml/AddProductTypeScreen.fxml";
                break;
            case EDIT_PRODUCT_TYPE_FRAGMENT:
                fileName = "/fxml/EditProductTypeFragment.fxml";
                break;
            default:
                throw new RuntimeException("FXML file path not added");
        }

        return fileName;
    }
}
