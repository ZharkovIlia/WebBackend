package zharkov.projects.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@JsonIgnoreProperties({"commentsById"})
@Entity
@Table(name = "users", schema = "public", catalog = "samizdat",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}, name = "users_email_key"),
                             @UniqueConstraint(columnNames = {"login"}, name = "users_login_key")})
public class UserEntity {
    private int userId;
    private String login;
    private String email;
    private List<CommentEntity> commentsById = new ArrayList<>();

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
    @SequenceGenerator(
            initialValue = 1,
            allocationSize = 1,
            name = "UserIdGenerator",
            schema = "public",
            catalog = "ilya",
            sequenceName = "users_user_id_seq"
    )
    public int getUserId() {
        return userId;
    }

    protected void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login", nullable = false, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(cascade = ALL)
    @JoinColumn(name="user_id")
    public List<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(List<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }
}
