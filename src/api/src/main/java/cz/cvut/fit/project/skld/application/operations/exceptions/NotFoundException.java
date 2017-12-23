package cz.cvut.fit.project.skld.application.operations.exceptions;

/**
 * Hledany objekt nebyl nalezen.
 */
public class NotFoundException extends Exception {
    /**
     * Konstruktor.
     */
    public NotFoundException() {
        super("The requested resource was not found.");
    }
}
