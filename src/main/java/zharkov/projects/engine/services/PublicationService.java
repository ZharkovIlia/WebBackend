package zharkov.projects.engine.services;

import org.hibernate.Session;
import org.hibernate.Transaction;
import zharkov.projects.engine.dao.PublicationDAO;
import zharkov.projects.engine.dao.PublicationLikeDAO;
import zharkov.projects.engine.dao.TagPublicationDAO;
import zharkov.projects.engine.dao.UserDAO;
import zharkov.projects.model.entities.PublicationEntity;
import zharkov.projects.model.entities.TagPublicationEntity;
import zharkov.projects.model.entities.UserEntity;
import zharkov.projects.model.frontend.AuthenticationContainer;
import zharkov.projects.model.frontend.Publication;
import zharkov.projects.utils.HibernateUtil;
import zharkov.projects.utils.StringToIntConverterByTags;

import java.util.*;

public class PublicationService extends AbstractService<PublicationEntity> {
    private static final TagPublicationDAO tagPublicationDAO = new TagPublicationDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final PublicationLikeDAO publicationLikeDAO = new PublicationLikeDAO();


    public PublicationService() {
        super(new PublicationDAO());
    }

    public Collection<Publication> getPublicationsByVisibility(AuthenticationContainer authContainer, boolean published) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PublicationDAO dao = (PublicationDAO) getDao();
        List<PublicationEntity> entities;
        List<TagPublicationEntity> tagPublicationEntities;
        Set<Integer> publicationIdsLiked = null;
        UserEntity user = null;
        boolean isAnonimous = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = userDAO.getUserByLogin(session, authContainer.getLogin());
            isAnonimous = (user == null || !user.getPassword().equals(authContainer.getPassword()));
            entities = dao.getPublicationsByVisibility(session, published);
            tagPublicationEntities = tagPublicationDAO.getAll(session);

            if (!isAnonimous) {
                publicationIdsLiked = publicationLikeDAO.getPublicationIdsByUserId(session, user.getUserId());
            }
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
            if (!isAnonimous && publicationIdsLiked.contains(entity.getPublicationId())) {
                result.get(entity.getPublicationId()).setCurrentUserLiked(true);
            }
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
