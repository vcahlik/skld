package cz.cvut.fit.si1.skld.client.controller.screen_controllers;

import cz.cvut.fit.si1.skld.client.controller.AppController;
import cz.cvut.fit.si1.skld.client.controller.ScreenController;
import cz.cvut.fit.si1.skld.client.model.domain.DummyUser;
import cz.cvut.fit.si1.skld.client.view.AppScreen;

public class LoginController extends ScreenController {
    public LoginController(AppController appController) {
        super(appController);
    }

    public void submitPassword(String password) {
        if (password != "") {
            getAppController().getModel().logIn(new DummyUser());
            getAppController().changeScreen(AppScreen.MAIN_MENU);
        } else {

        }
    }
}
