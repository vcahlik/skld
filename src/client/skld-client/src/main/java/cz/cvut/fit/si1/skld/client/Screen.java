package cz.cvut.fit.si1.skld.client;

public abstract class Screen extends Fragment implements Passable {
    App app;

    public Screen(Passable source) {
        super(source);

        this.app = source.getApp();
    }

    @Override
    public void follow() {
        getSource().setScreen(this);
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
    }

    @Override
    public App getApp() {
        return this.app;
    }

    @Override
    public void setScreen(Screen screen) {
        getSource().setScreen(screen);
    }

    public Passable getSource() {
        return (Passable)parent;
    }
}
