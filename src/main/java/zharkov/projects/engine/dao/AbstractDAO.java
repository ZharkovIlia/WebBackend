package zharkov.projects.engine.dao;

import org.hibernate.Session;
import zharkov.projects.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<T> {
    private Class<T> typeParameterClass;

    public AbstractDAO(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public Serializable save(Session session, T entity) {
        Serializable result = session.save(entity);
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll(Session session) {
        return (List<T>) session.createCriteria(typeParameterClass).list();
    }

    public T get(Session session, int id) {
        return (T) session.get(typeParameterClass, id);
    }

    public void update(Session session, T entity) {
        session.update(entity);
    }

    public boolean delete(Session session, int id) {
        T entity = session.get(typeParameterClass, id);
        boolean result = false;
        if (entity != null) {
            session.delete(entity);
            result = true;
        }
        return result;
    }
}
