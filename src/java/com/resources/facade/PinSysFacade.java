package com.resources.facade;

import com.resources.bean.NotUsedPinSys;
import com.resources.bean.UsedPinSys;
import com.resources.entity.Module;
import com.resources.entity.PinSys;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.utils.StringUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class PinSysFacade extends AbstractFacade {

    public PinSysFacade() {
        super(PinSys.class);
    }

    public int insertPinSys(Integer count, Integer rankCustomerId, Integer admId) {
        Session session = null;
        Transaction tran = null;
        Integer result = 0;
        try {
            System.out.println(count + " - " + rankCustomerId + " - " + admId);
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                tran = session.beginTransaction();
                Query q = session.createSQLQuery("InsertPinSys :count,:rankCustomerId,:admId")
                        .setParameter("count", count)
                        .setParameter("rankCustomerId", rankCustomerId)
                        .setParameter("admId", admId);
                result = (Integer) q.uniqueResult();
                tran.commit();
            }
        } catch (Exception e) {
            if (tran != null) {
                tran.rollback();
            }
            result = 0;
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public void pageDataNotUsed(HistoryPagination pagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(PinSys.class, "p");
                cr.add(Restrictions.and(Restrictions.eq("isDeleted", false), Restrictions.eq("isUsed", false)));
                List<String> listKeywords = pagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(pagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + pagination.getSearchString() + "%'"));
                }
                cr.add(disj);
                cr.setProjection(Projections.rowCount());
                pagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.createAlias("p.createdAdm", "admin");
                cr.createAlias("p.pinType", "rank");
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("pinNumber"), "pinNumber")
                        .add(Projections.property("rank.name"), "pinType")
                        .add(Projections.property("rank.pricePv"), "pricePv")
                        .add(Projections.property("admin.userName"), "createdAdm")
                        .add(Projections.property("createdDate"), "createdDate"))
                        .setResultTransformer(Transformers.aliasToBean(NotUsedPinSys.class));
                cr.setFirstResult(pagination.getFirstResult());
                cr.setMaxResults(pagination.getDisplayPerPage());
                cr.addOrder(pagination.isAsc() ? Order.asc(pagination.getOrderColmn()) : Order.desc(pagination.getOrderColmn()));
                pagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageDataUsedPinSys(com.resources.pagination.index.HistoryPagination pagination, Integer cusId) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(PinSys.class, "p");
                cr.createAlias("p.usedCustomer", "cus");
                cr.add(Restrictions.eq("isDeleted", false));
                cr.add(Restrictions.eq("isUsed", true));
                cr.add(Restrictions.eq("cus.id", cusId));
                cr.setProjection(Projections.rowCount());
                pagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.createAlias("p.pinType", "rank");
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("pinNumber"), "pinNumber")
                        .add(Projections.property("rank.name"), "pinType")
                        .add(Projections.property("rank.pricePv"), "pricePv")
                        .add(Projections.property("usedDate"), "usedDate"))
                        .setResultTransformer(Transformers.aliasToBean(UsedPinSys.class));
                cr.setFirstResult(pagination.getFirstResult());
                cr.setMaxResults(pagination.getDisplayPerPage());
                cr.addOrder(pagination.isAsc() ? Order.asc(pagination.getOrderColmn()) : Order.desc(pagination.getOrderColmn()));
                pagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageDataUsedPinSys(HistoryPagination pagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(PinSys.class, "p");
                cr.createAlias("p.usedCustomer", "cus");
                cr.createAlias("p.createdAdm", "admin");
                cr.createAlias("p.pinType", "rank");
                cr.add(Restrictions.eq("isDeleted", false));
                cr.add(Restrictions.eq("isUsed", true));

                List<String> listKeywords = pagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(pagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + pagination.getSearchString() + "%'"));
                }
                cr.add(disj);

                cr.setProjection(Projections.rowCount());
                pagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("cus.userName"), "usedCustomer")
                        .add(Projections.property("pinNumber"), "pinNumber")
                        .add(Projections.property("rank.name"), "pinType")
                        .add(Projections.property("rank.pricePv"), "pricePv")
                        .add(Projections.property("usedDate"), "usedDate")
                        .add(Projections.property("admin.userName"), "createdAdm")
                        .add(Projections.property("createdDate"), "createdDate"))
                        .setResultTransformer(Transformers.aliasToBean(UsedPinSys.class));
                cr.setFirstResult(pagination.getFirstResult());
                cr.setMaxResults(pagination.getDisplayPerPage());
                cr.addOrder(pagination.isAsc() ? Order.asc(pagination.getOrderColmn()) : Order.desc(pagination.getOrderColmn()));
                pagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public PinSys find(String pinNumber) {
        Session session = null;
        PinSys obj = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(PinSys.class);
                cr.add(Restrictions.eq("pinNumber", pinNumber));
                cr.add(Restrictions.eq("isDeleted", false));
                obj = (PinSys) cr.uniqueResult();
                if (obj != null) {
                    Hibernate.initialize(obj.getPinType());
                }
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return obj;
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
