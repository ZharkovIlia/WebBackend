package zharkov.projects.engine.dao;

import org.hibernate.Session;
import zharkov.projects.model.entities.PublicationEntity;

import java.util.List;

public class PublicationDAO extends AbstractDAO<PublicationEntity> {
    public PublicationDAO() {
        super(PublicationEntity.class);
    }

    @SuppressWarnings("unchecked")
    public List<PublicationEntity> getPublicationsByVisibility(Session session, boolean published) {
        List<PublicationEntity> result = (List<PublicationEntity>) session
                .createQuery("FROM PublicationEntity pe WHERE pe.published = :published")
                .setBoolean("published", published)
                .list();
        return result;
    }
}
