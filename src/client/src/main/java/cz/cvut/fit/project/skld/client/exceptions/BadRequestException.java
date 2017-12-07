package cz.cvut.fit.project.skld.client.exceptions;

public class BadRequestException extends APIException {
    public BadRequestException() {
        super("The request you performed is invalid. Please try again.", 400);
    }
}
