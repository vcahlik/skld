package cz.cvut.fit.project.skld.api.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

public class WebAppExceptionSupplier implements Supplier<WebApplicationException> {
    private final String message;
    private final Response.Status status;

    public WebAppExceptionSupplier(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    public WebApplicationException get() {
        return new WebApplicationException(message, status);
    }
}
