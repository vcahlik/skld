package cz.cvut.fit.project.skld.client.exceptions;

/**
 * Reprezentace vyjimky specificke pro datovou vrstvu klienta.
 */
public abstract class APIException extends Exception {
    private int code;

    protected APIException(String message, int code) {
        super(message);
        this.code = code;
    }

    /**
     * Vraci chybovy kod vyjimky.
     * @return Kod vyjimky
     */
    public int getCode() {
        return code;
    }

    /**
     * Nastavuje chybovy kod vyjimky
     * @param code Kod vyjimky
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Vytvori specificky objekt vyjimky podle daneho chyboveho kodu.
     * @param code Chybovy kod
     * @return Instance vyjimky
     */
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
