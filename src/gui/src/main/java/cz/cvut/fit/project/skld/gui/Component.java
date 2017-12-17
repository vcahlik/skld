package cz.cvut.fit.project.skld.gui;

/**
 * Zakladni trida vsech prvku GUI - oken, obrazovek, tlacitek...
 */
public abstract class Component implements UI, Notifyable {
    private Notifyable parent;

    /**
     * Konstruktor
     * @param parent Rodic, ktery obsahuje tuto komponentu (nebo ze ktereho byla vytvorena)
     */
    public Component(Notifyable parent) {
        this.parent = parent;
    }

    /**
     * Preposle notifikaci rodicovskemu objektu.
     * @param source Objekt, ktery odeslal notifikaci
     * @param notifyType Typ notifikace
     */
    @Override
    public void notify(UI source, NotifyType notifyType) {
        getParent().notify(this, notifyType);
    }

    /**
     * Vraci referenci na celou aplikaci.
     * @return Aplikace
     */
    @Override
    public App getApp() {
        return parent.getApp();
    }

    /**
     * Vraci rodice, ktery obsahuje tuto komponentu (nebo ze ktereho byla vytvorena)
     * @return Rodic
     */
    public Notifyable getParent() {
        return parent;
    }
}
