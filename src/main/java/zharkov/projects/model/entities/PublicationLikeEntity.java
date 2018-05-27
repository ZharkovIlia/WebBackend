package zharkov.projects.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "publication_likes", schema = "public", catalog = "samizdat")
@IdClass(PublicationLikeEntityPK.class)
public class PublicationLikeEntity {
    private int userId;
    private int publicationId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "publication_id", nullable = false)
    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationLikeEntity that = (PublicationLikeEntity) o;

        if (userId != that.userId) return false;
        if (publicationId != that.publicationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + publicationId;
        return result;
    }
}
