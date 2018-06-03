package zharkov.projects.engine.dao;

import org.hibernate.Session;
import zharkov.projects.model.entities.PublicationLikeEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PublicationLikeDAO extends AbstractDAO<PublicationLikeEntity> {
    public PublicationLikeDAO() {
        super(PublicationLikeEntity.class);
    }

    @SuppressWarnings("unchecked")
    public Set<Integer> getPublicationIdsByUserId(Session session, int userId) {
        return new HashSet<>(((List<Integer>) session
                .createQuery("SELECT ple.publicationId FROM PublicationLikeEntity ple WHERE ple.userId = :userId")
                .setInteger("userId", userId)
                .list()));
    }
}
