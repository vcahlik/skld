package cz.cvut.fit.project.skld.gui.components.login_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.client.exceptions.UnauthorizedException;
import cz.cvut.fit.project.skld.client.http.SkldHttpClient;
import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.resources.Config;
import cz.cvut.fit.project.skld.gui.resources.Texts;
import cz.cvut.fit.project.skld.gui.util.FXUtil;
import cz.cvut.fit.project.skld.*;
import cz.cvut.fit.project.skld.gui.components.main_menu.MainMenuScreen;

import java.io.IOException;
import java.net.ConnectException;

public class LoginScreen extends Screen {
    LoginScreenHandler handler;

    public LoginScreen(Passable source) {
        super(source);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new LoginScreenHandler();
        handler.setOwner(this);
        return handler;
    }

    @Override
    public void pass(UI follower, PassResult result) {
        getSource().changeContent(this);
    }

    public void submitPassword(String password) {
        SkldHttpClient httpClient = null;

        try {
             httpClient = SkldHttpClient.getClientForPIN(Config.getInstance().getServerUrl(), password);
        } catch (UnauthorizedException e) {
            handler.onInvalidPassword();
            return;
        } catch (IOException | APIException e) {
            FXUtil.displayAlert(Texts.Alerts.CONNECTION_ERROR_ALERT_TITLE, Texts.Alerts.CONNECTION_ERROR_ALERT_TEXT);
            return;
        }

        getApp().setClient(httpClient);

        MainMenuScreen screen = new MainMenuScreen(this);
        screen.follow();
    }
}
