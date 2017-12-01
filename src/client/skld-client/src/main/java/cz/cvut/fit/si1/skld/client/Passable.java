package cz.cvut.fit.si1.skld.client;

public interface Passable extends Notifyable {
    void follow();

    void pass(UI source, PassResult result);

    App getApp();

    void setScreen(Screen screen);

}
