package cz.cvut.fit.project.skld.application.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.function.Supplier;

/**
 * Implementace interfejsu Supplier pres WebAppExceptions.
 */
public class WebAppExceptionSupplier implements Supplier<WebApplicationException> {
    private final String message;
    private final Response.Status status;

    /**
     * Konstruktor. Vytvori noveho Suppliera s prednastavenou WebApplicationException se zadanou zpravou a statusem.
     * @param message Zprava vyjimky
     * @param status Status vyjimky
     */
    public WebAppExceptionSupplier(String message, Response.Status status) {
        this.message = message;
        this.status = status;
    }

    /**
     * Vraci exception s parametry, ktere byly specifikovany v konstruktoru.
     */
    public WebApplicationException get() {
        return new WebApplicationException(message, status);
    }
}
