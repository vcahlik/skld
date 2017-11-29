package cz.cvut.fit.si1.skld.client.view.fxview.handlers;

import cz.cvut.fit.si1.skld.client.controller.AppController;
import cz.cvut.fit.si1.skld.client.controller.screen_controllers.MainMenuController;
import cz.cvut.fit.si1.skld.client.view.fxview.FXHandler;

public class MainMenuScreenHandler implements FXHandler {
    private MainMenuController controller;

    @Override
    public void setController(AppController appController) {
        if (this.controller != null) {
            throw new IllegalStateException("Controller can be set only once");
        }

        this.controller = new MainMenuController(appController);
    }
}
