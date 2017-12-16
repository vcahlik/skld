package cz.cvut.fit.project.skld.application.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

/***
 * An implementation of Supplier interface over WebAppExceptions.
 */
public class WebAppExceptionSupplier implements Supplier<WebApplicationException> {
    private final String message;
    private final Response.Status status;

    /***
     * Construct a new supplier which will return WebApplicationException with the given message and status when calling
     * its get() method.
     */
    public WebAppExceptionSupplier(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    /***
     * Get the exception with the parameters specified in the constructor.
     */
    public WebApplicationException get() {
        return new WebApplicationException(message, status);
    }
}
