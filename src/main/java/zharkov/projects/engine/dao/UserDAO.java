package zharkov.projects.engine.dao;

import org.hibernate.Session;
import zharkov.projects.model.entities.UserEntity;

public class UserDAO extends AbstractDAO<UserEntity> {
    public UserDAO() {
        super(UserEntity.class);
    }

    public UserEntity getUserByLogin(Session session, String login) {
        return (UserEntity) session.createQuery("FROM UserEntity ue WHERE ue.login = :login")
                .setString("login", login)
                .uniqueResult();
    }

    public UserEntity getUserByEmail(Session session, String email) {
        return (UserEntity) session.createQuery("FROM UserEntity ue WHERE ue.email = :email")
                .setString("email", email)
                .uniqueResult();
    }
}
