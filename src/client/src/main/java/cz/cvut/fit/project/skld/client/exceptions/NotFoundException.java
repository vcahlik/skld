package cz.cvut.fit.project.skld.client.exceptions;

public class NotFoundException extends APIException {
    public NotFoundException() {
        super("Entity Not Found", 404);
    }
}
