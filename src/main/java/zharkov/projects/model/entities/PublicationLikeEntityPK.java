package zharkov.projects.model.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class PublicationLikeEntityPK implements Serializable{
    private int userId;
    private int publicationId;

    @Column(name = "user_id", nullable = false)
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "publication_id", nullable = false)
    @Id
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

        PublicationLikeEntityPK that = (PublicationLikeEntityPK) o;

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
