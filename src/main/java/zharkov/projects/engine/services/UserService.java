package zharkov.projects.engine.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zharkov.projects.engine.dao.AbstractDAO;
import zharkov.projects.engine.dao.UserDAO;
import zharkov.projects.model.entities.UserEntity;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.model.frontend.User;
import zharkov.projects.model.frontend.UserSensitive;
import zharkov.projects.utils.HibernateUtil;

public class UserService extends AbstractService<UserEntity> {
    public UserService() {
        super(new UserDAO());
    }

    public UserSensitive getUserByCredentials(AuthenticationContainer authContainer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserDAO dao = (UserDAO) getDao();
        UserEntity user;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = dao.getUserByLogin(session, authContainer.getLogin());
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        if (user != null && user.getPassword().equals(authContainer.getPassword())) {
            return new UserSensitive(user);
        } else {
            return null;
        }
    }

    public User getUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserDAO dao = (UserDAO) getDao();
        UserEntity user;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = dao.get(session, id);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        if (user != null) {
            return new User(user);
        } else {
            return null;
        }
    }

    public UserSensitive createUser(UserEntity user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserDAO dao = (UserDAO) getDao();
        Transaction tx = null;
        boolean created = false;
        try {
            tx = session.beginTransaction();
            UserEntity withEqualEmail = dao.getUserByEmail(session, user.getEmail());
            UserEntity withEqualLogin = dao.getUserByLogin(session, user.getLogin());
            if (withEqualEmail == null && withEqualLogin == null) {
                dao.save(session, user);
                created = true;
            }
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        if (created) {
            return new UserSensitive(user);
        }
        return null;
    }
}
