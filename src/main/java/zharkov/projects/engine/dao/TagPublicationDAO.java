package zharkov.projects.engine.dao;

import org.hibernate.Session;
import zharkov.projects.model.entities.PublicationEntity;
import zharkov.projects.model.entities.TagPublicationEntity;
import zharkov.projects.model.frontend.Publication;
import zharkov.projects.utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class TagPublicationDAO extends AbstractDAO<TagPublicationEntity> {
    public TagPublicationDAO() {
        super(TagPublicationEntity.class);
    }

    public List<TagPublicationEntity> getAll(Session s) {
        List<TagPublicationEntity> result = (List<TagPublicationEntity>) s.createCriteria(TagPublicationEntity.class).list();
        return result;
    }

    /*public List<Publication> enrichPublicationsWithTags(List<PublicationEntity> entities) {
        List<Publication> result = new ArrayList<>(entities.size());
        HibernateUtil.getCurrentSession()
                .createQuery("FROM TagPublicationEntity tpe join PublicationEntity pe")
    }*/


}
