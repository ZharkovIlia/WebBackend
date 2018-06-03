package zharkov.projects.model.frontend;

import lombok.Getter;
import lombok.Setter;
import zharkov.projects.model.entities.UserEntity;

public class User {
    private @Getter @Setter int userId;
    private @Getter @Setter String firstName;
    private @Getter @Setter String lastName;
    private @Getter @Setter String about;
    private @Getter @Setter String photo;

    public User(UserEntity user) {
        userId = user.getUserId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        about = user.getAbout();
        photo = user.getPhoto();
    }
}
