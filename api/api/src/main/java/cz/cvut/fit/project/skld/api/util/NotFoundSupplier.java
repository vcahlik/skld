package cz.cvut.fit.project.skld.api.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

public class NotFoundSupplier implements Supplier<WebApplicationException> {
    private final String entity;

    public NotFoundSupplier(String entity) {
        this.entity = entity;
    }

    public WebApplicationException get() {
        return new WebApplicationException(String.format("%s was not found", entity), Response.Status.NOT_FOUND);
    }
}
