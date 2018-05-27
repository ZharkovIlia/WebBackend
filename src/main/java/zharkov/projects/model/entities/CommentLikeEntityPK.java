package zharkov.projects.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class CommentLikeEntityPK implements Serializable {
    private int userId;
    private int commentId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "comment_id", nullable = false)
    @Id
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentLikeEntityPK that = (CommentLikeEntityPK) o;

        if (userId != that.userId) return false;
        if (commentId != that.commentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + commentId;
        return result;
    }
}
