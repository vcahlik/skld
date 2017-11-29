package cz.cvut.fit.si1.skld.client.controller;

public class ScreenController implements Controller {
    private AppController appController;

    public ScreenController(AppController appController) {
        this.appController = appController;
    }

    public AppController getAppController() {
        return appController;
    }

}
