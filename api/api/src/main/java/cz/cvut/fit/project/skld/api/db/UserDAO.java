package cz.cvut.fit.project.skld.api.db;

import cz.cvut.fit.project.skld.api.core.Product;
import cz.cvut.fit.project.skld.api.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public Optional<User> findByPin(String pin) {
        Query<User> q = namedQuery("User.findByPIN");
        q.setParameter("pin", pin);
        List<User> found = list(q);
        if (found.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(found.get(0));
        }
    }

    public User create(User user) {
        return persist(user);
    }

}
