package cz.cvut.fit.project.skld.client.exceptions;

public class ServerException extends APIException {
    public ServerException() {
        super("There was an error at the remote server. Please, try again.", 500);
    }
}
