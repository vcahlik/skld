package cz.cvut.fit.project.skld.gui.components.navigator.navigator_bar;

import cz.cvut.fit.project.skld.gui.Fragment;
import cz.cvut.fit.project.skld.gui.Handler;
import cz.cvut.fit.project.skld.gui.NotifyType;
import cz.cvut.fit.project.skld.gui.Notifyable;

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
