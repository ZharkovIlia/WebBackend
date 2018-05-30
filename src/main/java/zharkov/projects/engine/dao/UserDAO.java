package zharkov.projects.engine.dao;

import zharkov.projects.model.entities.UserEntity;

public class UserDAO extends AbstractDAO<UserEntity> {
    public UserDAO() {
        super(UserEntity.class);
    }
}
