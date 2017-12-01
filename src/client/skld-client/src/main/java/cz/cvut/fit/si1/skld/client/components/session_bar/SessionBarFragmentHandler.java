package cz.cvut.fit.si1.skld.client.components.session_bar;

import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.resources.FXMLFragment;

public class SessionBarFragmentHandler extends Handler {
    SessionBarFragment owner;

    public SessionBarFragmentHandler() {
        super(FXMLFragment.SESSION_BAR_FRAGMENT);
    }

    public void setOwner(SessionBarFragment owner) {
        this.owner = owner;
    }
}
