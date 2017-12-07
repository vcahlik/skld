package cz.cvut.fit.project.skld.client.exceptions;

public class NonspecificException extends APIException {
    public NonspecificException() {
        super("An error has ocurred. That's all we know.", -1);
    }
}
