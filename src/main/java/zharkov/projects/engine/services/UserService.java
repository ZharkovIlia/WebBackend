package zharkov.projects.engine.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zharkov.projects.engine.dao.AbstractDAO;
import zharkov.projects.engine.dao.UserDAO;
import zharkov.projects.model.entities.UserEntity;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.utils.HibernateUtil;

public class UserService extends AbstractService<UserEntity> {
    public UserService() {
        super(new UserDAO());
    }

    public UserEntity getUserByCredentials(AuthenticationContainer authContainer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        UserDAO dao = (UserDAO) getDao();
        UserEntity user = null;
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
            return user;
        } else {
            return null;
        }
    }
}
