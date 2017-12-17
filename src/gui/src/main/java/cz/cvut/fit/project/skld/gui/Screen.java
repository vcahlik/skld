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

    /**
     * V rodicovskem wrapperu zobrazi tuto zobrazovku misto libovolne predesle.
     */
    @Override
    public void follow() {
        getSource().changeContent(this);
    }

    /**
     * V rodicovskem wrapperu zobrazi tuto zobrazovku (obvykle misto dcerinne obrazovky zdroje).
     * @param source Dcerinny objekt
     * @param result Vysledek provadeni operaci dcerinneho objektu
     */
    @Override
    public void pass(UI source, PassResult result) {
        getSource().changeContent(this);
    }

    /**
     * Preposle rodicovske komponente pozadavek na zmenu obrazovky.
     * @param screen Nova obrazovka
     */
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
