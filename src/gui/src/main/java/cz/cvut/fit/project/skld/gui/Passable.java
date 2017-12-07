package cz.cvut.fit.project.skld.gui;

public interface Passable extends Notifyable {
    void follow();

    void pass(UI source, PassResult result);

    void changeContent(Screen screen);

}
