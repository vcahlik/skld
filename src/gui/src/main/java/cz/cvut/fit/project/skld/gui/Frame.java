package cz.cvut.fit.project.skld.gui;

/**
 * Wrapper, ktery obsahuje prostor pro obrazovku, ktera muze byt nahrazovana jinou obrazovkou.
 */
public abstract class Frame extends Screen implements Wrapper {
    /**
     * Konstruktor
     * @param source Rodic, ktery obsahuje tento ramec
     */
    public Frame(Passable source) {
        super(source);
    }
}
