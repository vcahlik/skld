package cz.cvut.fit.si1.skld.client;

import cz.cvut.fit.si1.skld.client.resources.FXMLFragment;

public abstract class Handler {
    private FXMLFragment fxmlFragment;

    public Handler(FXMLFragment fxmlFragment) {
        this.fxmlFragment = fxmlFragment;
    }

    public String getFXMLFilePath() {
        return fxmlFragment.getFXMLFilePath();
    }
}
