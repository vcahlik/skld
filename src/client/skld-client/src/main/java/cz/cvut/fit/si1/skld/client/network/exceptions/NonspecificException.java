package cz.cvut.fit.si1.skld.client.network.exceptions;

public class NonspecificException extends APIException {
    public NonspecificException() {
        super("An error has ocurred. That's all we know.", -1);
    }
}
