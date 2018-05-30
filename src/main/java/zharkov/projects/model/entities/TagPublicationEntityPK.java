package zharkov.projects.model.entities;

import zharkov.projects.utils.StringToIntConverterByTags;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import java.io.Serializable;

public class TagPublicationEntityPK implements Serializable {
    private String name;
    private int publicationId;

    @Column(name = "tag_id", nullable = false)
    @Id
    @Convert(converter = StringToIntConverterByTags.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        TagPublicationEntityPK that = (TagPublicationEntityPK) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publicationId != that.publicationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + publicationId;
        return result;
    }
}
