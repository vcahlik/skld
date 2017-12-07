package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.representations.LogInDetails;
import cz.cvut.fit.project.skld.representations.PIN;
import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.UserDAO;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import io.dropwizard.hibernate.UnitOfWork;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final UserDAO userDAO;
    private final byte[] tokenKey;

    public AuthResource(byte[] tokenKey, UserDAO uDao) {
        this.tokenKey = tokenKey;
        userDAO = uDao;
    }

    @POST
    @Path("/in")
    @UnitOfWork
    public LogInDetails logIn(PIN pinObject) {
        User user = userDAO.findByPin(pinObject.getPin()).orElseThrow(new WebAppExceptionSupplier("PIN incorrect or nonexistent", Response.Status.UNAUTHORIZED));
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(600);
        claims.setSubject(Long.toString(user.getId()));

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenKey));

        try {
            return new LogInDetails(RepresentationConverter.representUser(user), jws.getCompactSerialization());
        } catch (JoseException e) { throw new RuntimeException(e); }
    }
}
