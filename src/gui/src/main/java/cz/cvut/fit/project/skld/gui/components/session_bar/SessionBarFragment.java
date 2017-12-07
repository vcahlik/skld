package cz.cvut.fit.project.skld.gui.components.session_bar;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;

public class SessionBarFragment extends Fragment {
    SessionBarFragmentHandler handler;

    public SessionBarFragment(Notifyable parent) {
        super(parent);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new SessionBarFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public void logoutRequested() {
        getParent().notify(this, NotifyType.LOGOUT);
    }

}