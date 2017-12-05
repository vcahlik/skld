package cz.cvut.fit.si1.skld.client.components.login_screen;

import cz.cvut.fit.project.skld.client.exceptions.APIException;
import cz.cvut.fit.project.skld.client.exceptions.UnauthorizedException;
import cz.cvut.fit.project.skld.client.http.SkldHttpClient;
import cz.cvut.fit.si1.skld.client.*;
import cz.cvut.fit.si1.skld.client.components.main_menu.MainMenuScreen;
import cz.cvut.fit.si1.skld.client.resources.Config;
import cz.cvut.fit.si1.skld.client.resources.Texts;
import cz.cvut.fit.si1.skld.client.util.FXUtil;

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
             httpClient = SkldHttpClient.getClientForPIN(Config.SERVER_URL, password);
        } catch (UnauthorizedException e) {
            handler.onInvalidPassword();
            return;
        } catch (ConnectException e) {
            FXUtil.displayAlert(Texts.Alerts.CONNECTION_ERROR_ALERT_TITLE, Texts.Alerts.CONNECTION_ERROR_ALERT_TEXT);
            return;
        } catch (IOException | APIException e) {
            e.printStackTrace();
            System.exit(1);
        }

        getApp().setHttpClient(httpClient);

        MainMenuScreen screen = new MainMenuScreen(this);
        screen.follow();
    }
}
