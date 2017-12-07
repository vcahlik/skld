package cz.cvut.fit.project.skld.application.db.postgres;

import cz.cvut.fit.project.skld.application.core.User;
import cz.cvut.fit.project.skld.application.db.UserDAO;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PostgresUserDAO extends AbstractDAO<User>implements UserDAO {
    public PostgresUserDAO(SessionFactory factory) {
        super(factory);
    }

    @Override public Optional<User> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    @Override public Optional<User> findByPin(String pin) {
        Query<User> q = namedQuery("User.findByPIN");
        q.setParameter("pin", pin);
        List<User> found = list(q);
        if (found.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(found.get(0));
        }
    }

    @Override public User create(User user) {
        return persist(user);
    }

}