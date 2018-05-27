package zharkov.projects.model.entities;

import zharkov.projects.utils.TagToIntConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Id;
import java.io.Serializable;

public class TagPublicationEntityPK implements Serializable {
    private Tag tag;
    private int publicationId;

    @Column(name = "tag_id", nullable = false)
    @Id
    @Convert(converter = TagToIntConverter.class)
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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

        if (tag.getDatabaseId() != that.tag.getDatabaseId()) return false;
        if (publicationId != that.publicationId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tag.getDatabaseId();
        result = 31 * result + publicationId;
        return result;
    }
}
