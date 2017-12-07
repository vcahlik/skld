package cz.cvut.fit.project.skld.gui;

public interface Notifyable {
    void notify(UI source, NotifyType notifyType);

    App getApp();
}
