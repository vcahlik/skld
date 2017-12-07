package cz.cvut.fit.si1.skld.client.components.navigator.navigator_bar;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.NotifyType;
import cz.cvut.fit.si1.skld.client.Notifyable;

public class NavigatorBarFragment extends Fragment {
    NavigatorBarFragmentHandler handler;

    public NavigatorBarFragment(Notifyable parent) {
        super(parent);
    }

    @Override
    public Handler makeHandler() {
        this.handler = new NavigatorBarFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public void goBackRequested() {
        getParent().notify(this, NotifyType.USER_ACTION);
    }
}
