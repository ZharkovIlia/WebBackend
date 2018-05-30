package zharkov.projects.engine.dao;

import zharkov.projects.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDAO<T> {
    private Class<T> typeParameterClass;

    public AbstractDAO(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    public Serializable save(T entity) {
        Serializable result = HibernateUtil.getCurrentSession().save(entity);
        return result;
    }

    public List<T> getAll() {
        List<T> result = (List<T>) HibernateUtil.getCurrentSession().createCriteria(typeParameterClass).list();
        return result;
    }

    public T get(int id) {
        T result = (T) HibernateUtil.getCurrentSession().get(typeParameterClass, id);
        return result;
    }

    public void update(T entity) {
        HibernateUtil.getCurrentSession().update(entity);
    }

    public boolean delete(int id) {
        T entity = HibernateUtil.getCurrentSession().get(typeParameterClass, id);
        boolean result = false;
        if (entity != null) {
            HibernateUtil.getCurrentSession().delete(entity);
            result = true;
        }
        return result;
    }
}
