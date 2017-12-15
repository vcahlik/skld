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

    @Override
    public void notify(UI source, NotifyType notifyType) {
        getParent().notify(this, notifyType);
    }

    @Override
    public App getApp() {
        return parent.getApp();
    }

    public Notifyable getParent() {
        return parent;
    }
}
