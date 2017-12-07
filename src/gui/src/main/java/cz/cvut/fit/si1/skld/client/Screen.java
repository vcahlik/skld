package cz.cvut.fit.si1.skld.client;

public abstract class Screen extends Fragment implements Passable {
    App app;

    public Screen(Passable source) {
        super(source);

        this.app = source.getApp();
    }

    @Override
    public void follow() {
        getSource().changeContent(this);
    }

    @Override
    public void notify(UI source, NotifyType notifyType) {
    }

    @Override
    public void pass(UI source, PassResult result) {
        getSource().changeContent(this);
    }

    @Override
    public void changeContent(Screen screen) {
        getSource().changeContent(screen);
    }

    public Passable getSource() {
        return (Passable)parent;
    }
}
