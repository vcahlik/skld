package cz.cvut.fit.project.skld.application.resources;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.UserDAO;
import cz.cvut.fit.project.skld.application.util.WebAppExceptionSupplier;
import cz.cvut.fit.project.skld.representations.LogInDetails;
import cz.cvut.fit.project.skld.representations.PIN;
import io.dropwizard.hibernate.UnitOfWork;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

/***
 * Implements REST endpoints which enable users to obtain login tokens necessary to perform other operations on the API.
 */
@Path("/log")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsResource.class);

    private final UserDAO userDAO;
    private final byte[] tokenKey;

    /***
     * Create a new AuthResource with the given configuration parameters.
     * @param tokenKey key which is used with JWT encryption/signature algorithms
     * @param uDao DAO used to access information about users.
     */
    public AuthResource(byte[] tokenKey, UserDAO uDao) {
        this.tokenKey = tokenKey;
        userDAO = uDao;
    }

    /***
     * Verify a PIN and return an access token and user's details, if the it's correct.
     * @param pinObject request payload, deserialized by Jersey
     * @return the response with user's details and the token
     */
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
