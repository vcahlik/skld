package cz.cvut.fit.project.skld.gui;

/**
 * Obrazovka je korenova komponenta Wrapperu (okna, ramu...). Obvykle muze byt v ramci Wrapperu nahrazena jinou. Muze ji byt predano rizeni.
 */
public abstract class Screen extends Fragment implements Passable {
    /**
     * Konstruktor
     * @param source Rodicovska komponenta (obvykle okno), ktera obsahuje tento fragment
     */
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

    /**
     * Vraci rodicovskou komponentu
     * @return Rodicovska komponenta
     */
    public Passable getSource() {
        return (Passable)getParent();
    }
}
