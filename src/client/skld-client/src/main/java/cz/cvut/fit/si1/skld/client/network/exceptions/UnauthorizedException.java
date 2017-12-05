package cz.cvut.fit.si1.skld.client.network.exceptions;

public class UnauthorizedException extends APIException {
    public UnauthorizedException() {
        super("You are not authorized to perform this operaion", 401);
    }
}
