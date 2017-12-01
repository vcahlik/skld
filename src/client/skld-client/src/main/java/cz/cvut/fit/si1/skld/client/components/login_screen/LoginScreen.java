package cz.cvut.fit.si1.skld.client.components.login_screen;

import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.main_menu.MainMenuScreen;
import cz.cvut.fit.si1.skld.client.domain.DummyUser;

public class LoginScreen extends Screen {
    public LoginScreen(Passable source) {
        super(source);
    }

    @Override
    public Handler makeHandler() {
        LoginScreenHandler handler = new LoginScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    @Override
    public void pass(UI source, PassResult result) {

    }

    public void submitPassword(String password) {
        getApp().getSession().logIn(new DummyUser());

        MainMenuScreen screen = new MainMenuScreen(this);
        screen.follow();
    }
}
