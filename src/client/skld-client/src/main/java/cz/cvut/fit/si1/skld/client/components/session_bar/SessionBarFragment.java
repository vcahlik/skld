package cz.cvut.fit.si1.skld.client.components.session_bar;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.NotifyType;
import cz.cvut.fit.si1.skld.client.Notifyable;

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
