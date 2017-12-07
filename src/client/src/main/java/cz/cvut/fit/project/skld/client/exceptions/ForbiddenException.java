package cz.cvut.fit.project.skld.client.exceptions;

public class ForbiddenException extends APIException {
    public ForbiddenException() {
        super("You do not have sufficient privileges to perform this operation.", 403);
    }
}
