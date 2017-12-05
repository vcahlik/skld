package cz.cvut.fit.si1.skld.client.network.exceptions;

import java.io.IOException;

public abstract class APIException extends Exception {
    private int code;

    protected APIException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static APIException forCode(int code) {
        switch (code) {
            case 400: return new BadRequestException();
            case 401: return new UnauthorizedException();
            case 403: return new ForbiddenException();
            case 404: return new NotFoundException();
            case 500: return new ServerException();
            default:  return new NonspecificException();
        }
    }
}
