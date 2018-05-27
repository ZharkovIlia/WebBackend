package zharkov.projects.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "comment_likes", schema = "public", catalog = "samizdat")
@IdClass(CommentLikeEntityPK.class)
public class CommentLikeEntity {
    private int userId;
    private int commentId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "comment_id", nullable = false)
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

        CommentLikeEntity that = (CommentLikeEntity) o;

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
