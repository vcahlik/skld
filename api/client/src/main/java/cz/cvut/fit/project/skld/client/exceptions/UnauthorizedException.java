package cz.cvut.fit.project.skld.client.exceptions;

public class UnauthorizedException extends APIException {
    public UnauthorizedException() {
        super("You are not authorized to perform this operaion", 401);
    }
}
