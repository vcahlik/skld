package cz.cvut.fit.project.skld.gui;

import cz.cvut.fit.project.skld.gui.resources.Files;

public abstract class Handler {
    private FXMLFragmentType fxmlFragmentType;

    public Handler(FXMLFragmentType fxmlFragmentType) {
        this.fxmlFragmentType = fxmlFragmentType;
    }

    public String getFXMLFilePath() {
        return Files.getFXMLFilePath(fxmlFragmentType);
    }
}
