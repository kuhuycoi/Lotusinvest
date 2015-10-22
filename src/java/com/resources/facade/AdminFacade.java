package com.resources.facade;

import com.resources.entity.Admin;
import com.resources.entity.Module;
import com.resources.function.CustomFunction;
import com.resources.pagination.admin.AdminPagination;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.utils.StringUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class AdminFacade extends AbstractFacade {

    public AdminFacade() {
        super(Admin.class);
    }

    public Admin login(Admin admin) throws Exception {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Criteria cr = session.createCriteria(Admin.class);
            cr.add(Restrictions.and(Restrictions.eq("userName", admin.getUserName()), Restrictions.eq("password", admin.getPassword())));
            admin = (Admin) cr.uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return admin;
    }

    public void pageData(AdminPagination adminPagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Admin.class, "a");
                cr.add(Restrictions.eq("a.isDelete", false));
                List<String> listKeywords = adminPagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(adminPagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + adminPagination.getSearchString() + "%'"));
                }
                cr.add(disj);

                cr.setProjection(Projections.rowCount());
                adminPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("userName"), "userName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("createdOnUtc"), "createdOnUtc")
                        .add(Projections.property("mobile"), "mobile")
                        .add(Projections.property("email"), "email")
                        .add(Projections.property("isActive"), "isActive"))
                        .setResultTransformer(Transformers.aliasToBean(Admin.class));
                cr.setFirstResult(adminPagination.getFirstResult());
                cr.setMaxResults(adminPagination.getDisplayPerPage());
                cr.addOrder(adminPagination.isAsc() ? Order.asc(adminPagination.getOrderColmn()) : Order.desc(adminPagination.getOrderColmn()));
                adminPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public int create(Admin admin) throws Exception {
        Transaction trans = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            if (admin == null) {
                return 1;
            }
            if (StringUtils.isEmpty(admin.getFirstName())
                    || StringUtils.isEmpty(admin.getLastName())
                    || StringUtils.isEmpty(admin.getUserName())
                    || StringUtils.isEmpty(admin.getPassword())) {
                return 2;
            }
            admin.setPassword(CustomFunction.md5(admin.getPassword()));
            admin.setFirstName(StringUtils.escapeHtmlEntity(admin.getFirstName()));
            admin.setLastName(StringUtils.escapeHtmlEntity(admin.getLastName()));
            admin.setUserName(StringUtils.escapeHtmlEntity(admin.getUserName()));
            admin.setMobile(StringUtils.escapeHtmlEntity(admin.getMobile()));
            admin.setEmail(StringUtils.escapeHtmlEntity(admin.getEmail()));
            admin.setAddress(StringUtils.escapeHtmlEntity(admin.getAddress()));
            admin.setTaxCode(StringUtils.escapeHtmlEntity(admin.getTaxCode()));
            admin.setBillingAddress(StringUtils.escapeHtmlEntity(admin.getBillingAddress()));
            admin.setIsActive(true);
            Admin a = findAdminByUsername(admin.getUserName());
            if (a != null) {
                return 3;
            }
            session.save(admin);
            session.save(admin);
            trans.commit();
            return 4;
        } catch (Exception e) {
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
    }

    public void delete(int id) throws Exception {
        Transaction trans = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                trans = session.beginTransaction();
                Query query = session.createSQLQuery("update admin set isDelete=:isDelete where id=:id").setParameter("isDelete", true).setParameter("id", id);
                query.executeUpdate();
                trans.commit();
            }
        } catch (Exception e) {
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
    }

    public void block(Integer id, Boolean status) throws Exception {
        Transaction trans = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                trans = session.beginTransaction();
                Query query = session.createSQLQuery("update admin set isActive=:isActive where id=:id").setParameter("isActive", status).setParameter("id", id);
                query.executeUpdate();
                trans.commit();
            }
        } catch (Exception e) {
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
    }

    public Admin findAdminByUsername(String userName) throws Exception {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Admin.class);
                cr.add(Restrictions.eq("userName", userName));
                cr.add(Restrictions.eq("isDelete", false));
                return (Admin) cr.uniqueResult();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return null;
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
