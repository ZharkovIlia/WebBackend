package zharkov.projects.engine.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zharkov.projects.engine.dao.PublicationDAO;
import zharkov.projects.engine.dao.TagPublicationDAO;
import zharkov.projects.model.entities.PublicationEntity;
import zharkov.projects.model.entities.TagPublicationEntity;
import zharkov.projects.model.frontend.Publication;
import zharkov.projects.utils.HibernateUtil;
import zharkov.projects.utils.StringToIntConverterByTags;

import java.util.*;

public class PublicationService extends AbstractService<PublicationEntity> {
    private static final TagPublicationDAO tagPublicationDAO = new TagPublicationDAO();

    public PublicationService() {
        super(new PublicationDAO());
    }

    public Collection<Publication> getPublicationsByVisibility(boolean published) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PublicationDAO dao = (PublicationDAO) getDao();
        List<PublicationEntity> entities;
        List<TagPublicationEntity> tagPublicationEntities;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            entities = dao.getPublicationsByVisibility(session, published);
            tagPublicationEntities = tagPublicationDAO.getAll(session);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }

        Map<Integer, Publication> result = new HashMap<>(entities.size());
        for (PublicationEntity entity : entities) {
            result.put(entity.getPublicationId(), new Publication(entity));
        }
        for (TagPublicationEntity entity : tagPublicationEntities) {
            if ( !result.containsKey(entity.getPublicationId())) {
                continue;
            }
            result.get(entity.getPublicationId())
                    .getTags()
                    .add(StringToIntConverterByTags.toString(entity.getTagId()));
        }
        return result.values();
    }
}
