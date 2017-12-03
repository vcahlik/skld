package cz.cvut.fit.si1.skld.client;

import cz.cvut.fit.si1.skld.client.resources.Files;

public abstract class Handler {
    private FXMLFragmentType fxmlFragmentType;

    public Handler(FXMLFragmentType fxmlFragmentType) {
        this.fxmlFragmentType = fxmlFragmentType;
    }

    public String getFXMLFilePath() {
        return Files.getFXMLFilePath(fxmlFragmentType);
    }
}
