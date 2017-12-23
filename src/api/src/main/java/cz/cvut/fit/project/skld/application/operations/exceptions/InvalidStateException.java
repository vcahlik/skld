package cz.cvut.fit.project.skld.application.operations.exceptions;

/**
 * Objekt je v nepovolenem/nesmyslnem stavu.
 */
public class InvalidStateException extends Exception {
    /**
     * Konstruktor.
     * @param message Zprava vyjimky
     */
    public InvalidStateException(String message) {
        super(message);
    }
}