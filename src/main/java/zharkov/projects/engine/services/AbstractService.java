package zharkov.projects.engine.services;

import lombok.Getter;
import org.hibernate.Session;
import zharkov.projects.engine.dao.AbstractDAO;
import zharkov.projects.utils.HibernateUtil;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T> {
    private @Getter AbstractDAO<T> dao;

    AbstractService(AbstractDAO<T> dao) {
        this.dao = dao;
    }

    public Serializable save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Serializable result;
        try {
            session.beginTransaction();
            result = dao.save(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<T> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<T> result;
        try {
            session.beginTransaction();
            result = dao.getAll();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public T get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T result;
        try {
            session.beginTransaction();
            result = dao.get(id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            dao.update(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            result = dao.delete(id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
