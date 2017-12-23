package cz.cvut.fit.project.skld.application.operations.exceptions;

import java.util.function.Supplier;

/**
 * Poskytovatel pro InvalidStateException.
 */
public class InvalidStateExceptionSupplier implements Supplier<InvalidStateException> {
    private final String message;

    /**
     * Konstruktor.
     * @param message Zprava zobrazena vyjimkou InvalidStateException
     */
    InvalidStateExceptionSupplier(String message) {
        this.message = message;
    }

    /**
     * Vraci vytvorenou InvalidStateException.
     * @return InvalidStateException
     */
    @Override
    public InvalidStateException get() {
        return new InvalidStateException(message);
    }
}
