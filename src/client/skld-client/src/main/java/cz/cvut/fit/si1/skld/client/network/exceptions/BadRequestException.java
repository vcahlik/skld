package cz.cvut.fit.si1.skld.client.network.exceptions;

public class BadRequestException extends APIException {
    public BadRequestException() {
        super("The request you performed is invalid. Please try again.", 400);
    }
}
