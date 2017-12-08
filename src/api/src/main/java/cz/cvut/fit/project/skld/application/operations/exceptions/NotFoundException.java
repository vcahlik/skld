package cz.cvut.fit.project.skld.application.operations.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("The requested resource was not found.");
    }
}
