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

/**
 * Overuje JWT data prijata z klienta a nahraje z databaze uzivatele s prislusnym heslem.
 */
public class UserAuthenticator implements Authenticator<JwtContext, User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticator.class);

    private final UserDAO userDAO;

    private SessionFactory sessionFactory;

    /**
     * Konstruktor.
     * @param dao UserDAO pro pristup k uzivatelum
     * @param sessionFactory Session pro pristup k databazi pres UserDAO. Viz dokumentace Dropwizard frameworku.
     */
    public UserAuthenticator(UserDAO dao, SessionFactory sessionFactory) {
        userDAO = dao;
        this.sessionFactory = sessionFactory;
    }

    /**
     * Validuje JWT data a pokusi se ziskat uzivatele s ID, ktere je ulozeno jako identifikator session.
     * @param jwtContext Identifikator session prislusneho requestu
     * @return Uzivatel
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
