package cz.cvut.fit.project.skld.application.operations.exceptions;

import java.util.function.Supplier;

public class InvalidStateExceptionSupplier implements Supplier<InvalidStateException> {
    private final String message;

    InvalidStateExceptionSupplier(String message) {
        this.message = message;
    }

    @Override
    public InvalidStateException get() {
        return new InvalidStateException(message);
    }
}
