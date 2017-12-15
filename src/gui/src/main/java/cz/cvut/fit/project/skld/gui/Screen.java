package cz.cvut.fit.project.skld.gui;

public abstract class Screen extends Fragment implements Passable {
    public Screen(Passable source) {
        super(source);
    }

    @Override
    public void follow() {
        getSource().changeContent(this);
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
        return (Passable)getParent();
    }
}
