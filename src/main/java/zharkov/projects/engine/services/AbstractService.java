package zharkov.projects.engine.services;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        Transaction tx = null;
        Serializable result;
        try {
            tx = session.beginTransaction();
            result = dao.save(session, entity);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<T> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<T> result;
        try {
            tx = session.beginTransaction();
            result = dao.getAll(session);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public T get(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        T result;
        try {
            tx = session.beginTransaction();
            result = dao.get(session, id);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            dao.update(session, entity);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public boolean delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            result = dao.delete(session, id);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
