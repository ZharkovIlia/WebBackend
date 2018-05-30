package zharkov.projects.engine.dao;

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

    /*public List<Publication> enrichPublicationsWithTags(List<PublicationEntity> entities) {
        List<Publication> result = new ArrayList<>(entities.size());
        HibernateUtil.getCurrentSession()
                .createQuery("FROM TagPublicationEntity tpe join PublicationEntity pe")
    }*/
}
