package cz.cvut.fit.project.skld.api.resources;

import cz.cvut.fit.project.skld.api.api.LogInDetails;
import cz.cvut.fit.project.skld.api.api.PIN;
import cz.cvut.fit.project.skld.api.core.User;
import cz.cvut.fit.project.skld.api.db.UserDAO;
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
import java.util.Optional;

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
        Optional<User> optUsr = userDAO.findByPin(pinObject.getPin());
        if (!optUsr.isPresent()) {
           throw new WebApplicationException("PIN incorrect or nonexistent", Response.Status.UNAUTHORIZED);
        }
        final JwtClaims claims = new JwtClaims();
        claims.setExpirationTimeMinutesInTheFuture(600);
        claims.setSubject(Long.toString(optUsr.get().getId()));

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(tokenKey));

        try {
            return new LogInDetails(optUsr.get(), jws.getCompactSerialization());
        } catch (JoseException e) { throw new RuntimeException(e); }
    }
}
