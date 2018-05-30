package zharkov.projects.model.entities;

import zharkov.projects.utils.StringToIntConverterByTags;

import javax.persistence.*;

@Entity
@Table(name = "tags_publications", schema = "public", catalog = "samizdat")
@IdClass(TagPublicationEntityPK.class)
public class TagPublicationEntity {
    private int tagId;
    private int publicationId;
    private PublicationEntity publicationByPublicationId;

    @Id
    @Column(name = "tag_id", nullable = false)
    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
        if (tagId != that.tagId) return false;
        if (publicationId != that.publicationId) return false;

        return true;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", referencedColumnName = "publication_id", insertable = false, updatable = false)
    public PublicationEntity getPublicationByPublicationId() {
        return publicationByPublicationId;
    }

    public void setPublicationByPublicationId(PublicationEntity publicationByPublicationId) {
        this.publicationByPublicationId = publicationByPublicationId;
        this.publicationId = publicationByPublicationId.getPublicationId();
    }

    @Override
    public int hashCode() {
        int result = tagId;
        result = 31 * result + publicationId;
        return result;
    }
}
