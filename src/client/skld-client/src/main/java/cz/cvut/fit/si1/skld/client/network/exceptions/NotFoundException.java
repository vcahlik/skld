package cz.cvut.fit.si1.skld.client.network.exceptions;

public class NotFoundException extends APIException {
    public NotFoundException() {
        super("Entity Not Found", 404);
    }
}
