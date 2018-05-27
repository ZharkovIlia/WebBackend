package zharkov.projects.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments_", schema = "public", catalog = "samizdat")
public class CommentEntity {
    private int commentId;
    private Integer userId;
    private Integer publicationId;
    private Timestamp creationTime;
    private int numLikes;
    private String text;
    private UserEntity userByUserId;
    private PublicationEntity publicationByPublicationId;

    @Id
    @Column(name = "comment_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CommentIdGenerator")
    @SequenceGenerator(
            initialValue = 1,
            allocationSize = 1,
            name = "CommentIdGenerator",
            schema = "public",
            catalog = "ilya",
            sequenceName = "comments__comment_id_seq"
    )
    public int getCommentId() {
        return commentId;
    }

    protected void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    protected void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name="publication_id", nullable = false)
    public Integer getPublicationId() {
        return publicationId;
    }

    protected void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    @Basic
    @Column(name = "creation_time", nullable = false)
    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Basic
    @Column(name = "num_likes", nullable = false)
    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    @Basic
    @Column(name = "text_", nullable = false, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentId != that.commentId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (publicationId != null ? !publicationId.equals(that.publicationId) : that.publicationId != null) return false;
        if (numLikes != that.numLikes) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (publicationId != null ? publicationId.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + numLikes;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
        this.userId = userByUserId.getUserId();
    }

    @ManyToOne
    @JoinColumn(name = "publication_id", referencedColumnName = "publication_id", insertable = false, updatable = false)
    public PublicationEntity getPublicationByPublicationId() {
        return publicationByPublicationId;
    }

    public void setPublicationByPublicationId(PublicationEntity publicationByPublicationId) {
        this.publicationByPublicationId = publicationByPublicationId;
        this.publicationId = publicationByPublicationId.getPublicationId();
    }
}
