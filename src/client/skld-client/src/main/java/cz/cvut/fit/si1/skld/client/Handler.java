package cz.cvut.fit.si1.skld.client;

public abstract class Handler {
    private FXMLFragment fxmlFragment;

    public Handler(FXMLFragment fxmlFragment) {
        this.fxmlFragment = fxmlFragment;
    }

    public String getFXMLFilePath() {
        return fxmlFragment.getFXMLFilePath();
    }
}
