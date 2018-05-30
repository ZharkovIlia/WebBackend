package zharkov.projects.engine.dao;

import zharkov.projects.model.PublicationVisibility;
import zharkov.projects.model.entities.PublicationEntity;
import zharkov.projects.utils.HibernateUtil;

import java.util.List;

public class PublicationDAO extends AbstractDAO<PublicationEntity> {
    public PublicationDAO() {
        super(PublicationEntity.class);
    }

    @SuppressWarnings("unchecked")
    public List<PublicationEntity> getPublicationsByType(PublicationVisibility pv) {
        List<PublicationEntity> result = (List<PublicationEntity>) HibernateUtil
                .getCurrentSession()
                .createQuery("FROM PublicationEntity pe WHERE pe.type = :type")
                .setInteger("type", pv.getDatabaseInteger())
                .list();
        return result;
    }
}
