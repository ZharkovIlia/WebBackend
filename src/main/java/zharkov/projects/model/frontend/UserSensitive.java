package zharkov.projects.model.frontend;

import lombok.Getter;
import lombok.Setter;
import zharkov.projects.model.entities.UserEntity;

public class UserSensitive extends User {
    private @Getter @Setter String login;
    private @Getter @Setter String email;
    private @Getter @Setter String password;

    public UserSensitive(UserEntity user) {
        super(user);
        login = user.getLogin();
        email = user.getEmail();
        password = user.getPassword();
    }
}
