package zharkov.projects.model.entities;

import zharkov.projects.utils.TagToIntConverter;

import javax.persistence.*;

@Entity
@Table(name = "tags_publications", schema = "public", catalog = "samizdat")
@IdClass(TagPublicationEntityPK.class)
public class TagPublicationEntity {
    private Tag tag;
    private int publicationId;

    @Id
    @Column(name = "tag_id", nullable = false)
    @Convert(converter = TagToIntConverter.class)
    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
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

        TagPublicationEntity that = (TagPublicationEntity) o;

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
