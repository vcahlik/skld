package cz.cvut.fit.project.skld.gui;

public abstract class Component implements UI, Notifyable {
    private App app;

    public Component(App app) {
        this.app = app;
    }

    @Override
    public App getApp() {
        return this.app;
    }
}
