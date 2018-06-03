package zharkov.projects.model.frontend;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationContainer {
    private @Getter @Setter String login;
    private @Getter @Setter String password;

    public AuthenticationContainer(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
