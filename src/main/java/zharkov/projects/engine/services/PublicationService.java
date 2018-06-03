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
import java.util.stream.Collectors;

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
        Map<Integer, Publication> result;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<PublicationEntity> entities = dao.getPublicationsByVisibility(session, published);
            List<TagPublicationEntity> tagPublicationEntities = tagPublicationDAO.getAll(session);
            result = new HashMap<>(entities.size());
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
            enrichPublicationsWithLikes(session, result.values(), authContainer);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result.values();
    }

    public Collection<Publication> getPublicationsByUserId(AuthenticationContainer authContainer, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PublicationEntity> entities;
        List<Publication> result;
        UserEntity user;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = userDAO.get(session, id);
            entities = (user == null ? null :
                    user.getPublicationsById()
                    .stream()
                    .filter(PublicationEntity::getPublished)
                    .collect(Collectors.toList()));

            result = convertEntitiesToPublications(session, entities);
            enrichPublicationsWithLikes(session, result, authContainer);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public Collection<Publication> getPublicationsOfCurrentUser(AuthenticationContainer authContainer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PublicationEntity> entities;
        List<Publication> result;
        UserEntity user;
        boolean isNotAuthorized = false;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = userDAO.getUserByLogin(session, authContainer.getLogin());
            if (user == null || !user.getPassword().equals(authContainer.getPassword())) {
                isNotAuthorized = true;
            }
            entities = (isNotAuthorized ? null : user.getPublicationsById());

            result = convertEntitiesToPublications(session, entities);
            if (!isNotAuthorized) {
                enrichPublicationsWithLikes(session, result, user.getUserId());
            }
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    private List<Publication> convertEntitiesToPublications(Session session,
                                                            Collection<PublicationEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(entity -> {
                    Publication publication = new Publication(entity);
                    publication.setTags(entity.getTagsById().stream()
                            .map(tag -> StringToIntConverterByTags.toString(tag.getTagId()))
                            .collect(Collectors.toList()));
                    return publication;
                })
                .collect(Collectors.toList());
    }

    private void enrichPublicationsWithLikes(Session session,
                                             Collection<Publication> publications,
                                             AuthenticationContainer authContainer) {
        if (publications == null) {
            return;
        }
        UserEntity user = userDAO.getUserByLogin(session, authContainer.getLogin());
        if (user == null || !user.getPassword().equals(authContainer.getPassword())) {
            return;
        }
        enrichPublicationsWithLikes(session, publications, user.getUserId());
    }

    private void enrichPublicationsWithLikes(Session session,
                                             Collection<Publication> publications,
                                             int id) {
        if (publications == null) {
            return;
        }
        Set<Integer> publicationIdsLiked = publicationLikeDAO.getPublicationIdsByUserId(session, id);
        publications.forEach(publication -> publication.
                setCurrentUserLiked(publicationIdsLiked.contains(publication.getId())));
    }
}
