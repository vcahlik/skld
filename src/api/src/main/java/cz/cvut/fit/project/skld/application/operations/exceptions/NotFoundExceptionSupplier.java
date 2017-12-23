package cz.cvut.fit.project.skld.application.operations.exceptions;

import java.util.function.Supplier;

/**
 * Poskytovatel pro NotFoundException.
 */
public class NotFoundExceptionSupplier implements Supplier<NotFoundException> {
    /**
     * Konstruktor.
     */
    public NotFoundExceptionSupplier() {}

    /**
     * Vraci vytvorenou NotFoundException.
     * @return NotFoundException
     */
    public NotFoundException get() {
        return new NotFoundException();
    }
}
