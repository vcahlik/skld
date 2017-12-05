package cz.cvut.fit.si1.skld.client.network.exceptions;

public class ForbiddenException extends APIException {
    public ForbiddenException() {
        super("You do not have sufficient privileges to perform this operation.", 403);
    }
}
