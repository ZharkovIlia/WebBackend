package zharkov.projects.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "publications", schema = "public", catalog = "samizdat")
public class PublicationEntity {
    private int publicationId;
    private Integer userId;
    private String name;
    private String description;
    private String time;
    private Timestamp creationTime;
    private int numLikes;
    private String text;
    private boolean published;
    private List<CommentEntity> commentsById = new ArrayList<>();
    private List<TagPublicationEntity> tagsById = new ArrayList<>();
    private UserEntity userByUserId;

    @Id
    @Column(name = "publication_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PublicationIdGenerator")
    @SequenceGenerator(
            initialValue = 1,
            allocationSize = 1,
            name = "PublicationIdGenerator",
            schema = "public",
            catalog = "ilya",
            sequenceName = "publications_publication_id_seq"
    )
    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
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
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name_", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "time_", nullable = true, length = -1)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "creation_time", nullable = true)
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

    @Basic
    @Column(name = "published", nullable = false)
    public boolean getPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationEntity that = (PublicationEntity) o;

        if (publicationId != that.publicationId) return false;
        if (numLikes != that.numLikes) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (creationTime != null ? !creationTime.equals(that.creationTime) : that.creationTime != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (published != that.published) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publicationId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (creationTime != null ? creationTime.hashCode() : 0);
        result = 31 * result + numLikes;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + Boolean.hashCode(published);
        return result;
    }

    @OneToMany(cascade = ALL)
    @JoinColumn(name="publication_id")
    public List<CommentEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(List<CommentEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(cascade = ALL)
    @JoinColumn(name="publication_id")
    public List<TagPublicationEntity> getTagsById() {
        return tagsById;
    }

    public void setTagsById(List<TagPublicationEntity> tagsById) {
        this.tagsById = tagsById;
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
}
