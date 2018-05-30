package zharkov.projects.engine.services;

import org.hibernate.Session;
import zharkov.projects.engine.dao.PublicationDAO;
import zharkov.projects.engine.dao.TagPublicationDAO;
import zharkov.projects.model.PublicationVisibility;
import zharkov.projects.model.entities.PublicationEntity;
import zharkov.projects.model.entities.TagPublicationEntity;
import zharkov.projects.model.frontend.Publication;
import zharkov.projects.utils.HibernateUtil;

import java.util.*;

public class PublicationService extends AbstractService<PublicationEntity> {
    private static final TagPublicationDAO tagPublicationDAO = new TagPublicationDAO();

    public PublicationService() {
        super(new PublicationDAO());
    }

    public Collection<Publication> getPublicationsByType(PublicationVisibility pv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PublicationDAO dao = (PublicationDAO) getDao();
        List<PublicationEntity> entities;
        List<TagPublicationEntity> tagPublicationEntities;
        try {
            session.beginTransaction();
            entities = dao.getPublicationsByType(pv);
            tagPublicationEntities = tagPublicationDAO.getAll();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }

        Map<Integer, Publication> result = new HashMap<>(entities.size());
        for (PublicationEntity entity : entities) {
            result.put(entity.getPublicationId(), new Publication(entity));
        }
        for (TagPublicationEntity entity : tagPublicationEntities) {
            result.get(entity.getPublicationId()).getTags().add(entity.getName());
        }
        return result.values();
    }
}
