package cz.cvut.fit.project.skld.gui.components.navigator;

import cz.cvut.fit.project.skld.gui.*;
import cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar.NavigatorBarFragment;
import cz.cvut.fit.project.skld.gui.components.session_bar.SessionBarFragment;
import cz.cvut.fit.project.skld.*;

public abstract class NavigatorFrame extends Frame {
    private NavigatorFrameHandler handler;

    private NavigatorBarFragment navigatorBarFragment;
    private Screen content;
    private SessionBarFragment sessionBarFragment;

    public NavigatorFrame(Passable source) {
        super(source);

        setNavigatorBarFragment(new NavigatorBarFragment(this));
        setSessionBarFragment(new SessionBarFragment(this));
    }

    @Override
    public void pass(UI source, PassResult result) {
        super.pass(source, result);

        getSource().pass(this, result);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new NavigatorFrameHandler();
        handler.setOwner(this);
        return handler;
    }

    @Override
    public void changeContent(Screen screen) {
        content = screen;
        handler.setContent(screen);
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
        if (notifyType == NotifyType.LOGOUT) {
            getSource().pass(this, PassResult.LOGOUT);
        } else if (source == navigatorBarFragment & notifyType == NotifyType.USER_ACTION) {
            getSource().pass(this, PassResult.CANCELED);
        }
    }

    public void setNavigatorBarFragment(NavigatorBarFragment navigatorBarFragment) {
        this.navigatorBarFragment = navigatorBarFragment;
        handler.setNavigatorBarFragment(navigatorBarFragment);
    }

    public void setSessionBarFragment(SessionBarFragment sessionBarFragment) {
        this.sessionBarFragment = sessionBarFragment;
        handler.setSessionBarFragment(sessionBarFragment);
    }
}
