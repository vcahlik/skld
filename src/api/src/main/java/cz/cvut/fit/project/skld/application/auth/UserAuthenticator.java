package cz.cvut.fit.project.skld.application.auth;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.UserDAO;
import io.dropwizard.auth.Authenticator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/***
 * Checks JWT data sent in request headers and loads associated user information from the database.
 */
public class UserAuthenticator implements Authenticator<JwtContext, User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticator.class);

    private final UserDAO userDAO;

    private SessionFactory sessionFactory;

    /***
     * Create a new UserAuthenticator, injecting the given UserDAO and using the given SessionFactory to create
     * database sessions used by the DAO.
     * @param dao UserDAO used to fetch users.
     * @param sessionFactory Dropwizard's AbstractDAOs need to be run within a managed session which is created
     *                       by adding a @UnitOfWork annotation to a method on a resource. Unfortunately, user
     *                       authentication does not run inside this "unit of work" so the user has to pass a
     *                       SessionFactory and we bind it to ManagedSessionContext to be able to use AbstractDAOs.
     */
    public UserAuthenticator(UserDAO dao, SessionFactory sessionFactory) {
        userDAO = dao;
        this.sessionFactory = sessionFactory;
    }

    /***
     * Validate the JWT data and try to get user with ID that's saved as JwtContext's subject.
     * @param jwtContext the session identifier sent with the request
     * @return Optionally returns the user which is the subject in the given JwtContext
     */
    @Override
    public Optional<User> authenticate(JwtContext jwtContext) {
        Session session = sessionFactory.openSession();
        try {
            ManagedSessionContext.bind(session);
            try {
                final String subject = jwtContext.getJwtClaims().getSubject();
                return userDAO.findById(Long.parseLong(subject));
            } catch (MalformedClaimException e) {
                LOGGER.error("Received a malformed JWT claim: {}", e);
                return Optional.empty();
            }
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
    }
}
