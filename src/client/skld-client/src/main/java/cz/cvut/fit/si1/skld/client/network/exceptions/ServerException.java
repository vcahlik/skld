package cz.cvut.fit.si1.skld.client.network.exceptions;

public class ServerException extends APIException {
    public ServerException() {
        super("There was an error at the remote server. Please, try again.", 500);
    }
}
