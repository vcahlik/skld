package cz.cvut.fit.project.skld.application.operations.exceptions;

import java.util.function.Supplier;

public class NotFoundExceptionSupplier implements Supplier<NotFoundException> {

    public NotFoundExceptionSupplier() {}

    public NotFoundException get() {
        return new NotFoundException();
    }
}
