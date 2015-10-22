package com.resources.facade;

import com.resources.pagination.admin.DefaultAdminPagination;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public abstract class AbstractFacade<T> implements Serializable {

    public final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) throws Exception {
        Transaction trans = null;
        Session session = null;
        T result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();

            if (entity == null) {
                throw new NullPointerException();
            }

            result = (T) session.save(entity);
            trans.commit();
            session.flush();
        } catch (HibernateException e) {
            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (Exception ex) {
                throw ex;
            }
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public void edit(T entity) throws Exception {
        Transaction trans = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();

            session.update(entity);

            trans.commit();
        } catch (Exception e) {
            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (Exception ex) {
                throw ex;
            }
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void remove(T entity) throws Exception {
        Transaction trans = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();

            session.delete(entity);
            trans.commit();
        } catch (Exception e) {
            try {
                if (trans != null) {
                    trans.rollback();
                }
            } catch (Exception ex) {
                throw ex;
            }
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
            throw new Exception(e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public T find(int id) {
        Session session = null;
        T obj = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            obj = (T) session.get(entityClass, id);
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return obj;
    }

    public List<T> findAll() {
        Session session = null;
        List<T> list = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {

                list = session.createCriteria(entityClass).list();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    protected int count(boolean hasIsDelete) {
        Session session = null;
        int cnt = 0;
        try {
            session = HibernateConfiguration.getInstance().openSession();

            Criteria cr = session.createCriteria(entityClass);
            cr.setProjection(Projections.rowCount());
            if (hasIsDelete) {
                cr.add(Restrictions.eq("isDelete", false));
            }
            cnt = ((Long) cr.uniqueResult()).intValue();
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return cnt;
    }

    public abstract void pageData(DefaultAdminPagination pagination);
}
