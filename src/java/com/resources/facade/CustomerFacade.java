package com.resources.facade;

import com.resources.bean.CustomerDistributor;
import com.resources.bean.CustomerFromTotalParent;
import com.resources.bean.CustomerNonActive;
import com.resources.bean.CustomerTree;
import com.resources.entity.Customer;
import com.resources.entity.CustomerType;
import com.resources.entity.Module;
import com.resources.entity.ProvincialAgencies;
import com.resources.entity.RankNow;
import com.resources.function.CustomFunction;
import com.resources.pagination.admin.CustomerPagination;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.utils.ArrayUtils;
import com.resources.utils.StringUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

public class CustomerFacade extends AbstractFacade {

    public CustomerFacade() {
        super(Customer.class);
    }

    public List<CustomerTree> getTreeCustomer(Integer firstId) {
        return getTreeCustomer(firstId, null);
    }

    public List<CustomerTree> getTreeCustomer(Integer firstId, Integer max_level) {
        Session session = null;
        List list = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session == null) {
                return null;
            }
            Query q = session.createSQLQuery("TreeCustomer :first_id,:max_level").addEntity(CustomerTree.class);
            q.setParameter("first_id", firstId);
            q.setParameter("max_level", max_level);
            list = q.list();
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    public List<CustomerDistributor> findAllCustomerForParentId(String searchString) {
        Session session = null;
        List<CustomerDistributor> list = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                if (StringUtils.isEmpty(searchString)) {
                    return null;
                }
                Criteria cr = session.createCriteria(RankNow.class, "r");
                cr.createAlias("r.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.not(Restrictions.eq("cus.id", 1)));
                cr.add(Restrictions.and(Restrictions.eq("cus.isDelete", false),
                        Restrictions.eq("cus.isActive", true),
                        Restrictions.like("cus.userName", searchString, MatchMode.ANYWHERE),
                        Restrictions.not(Restrictions.eq("cus.id", 0))));
                cr.add(Restrictions.or(Restrictions.eq("r.userId", 0), Restrictions.eq("r.userId1", 0)));
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("cus.id"), "id")
                        .add(Projections.property("cus.firstName"), "firstName")
                        .add(Projections.property("cus.lastName"), "lastName")
                        .add(Projections.property("cus.userName"), "userName"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
                list = cr.list();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    public List findAllCustomerForCustomerId(String searchString, String parentName) {
        Session session = null;
        List<CustomerDistributor> list = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                if (StringUtils.isEmpty(searchString)) {
                    return null;
                }
                Criteria cr = session.createCriteria(Customer.class);
                cr.add(Restrictions.and(Restrictions.eq("isDelete", false), Restrictions.eq("isActive", true), Restrictions.like("userName", searchString, MatchMode.ANYWHERE), Restrictions.not(Restrictions.eq("id", 1))));

                if (!StringUtils.isEmpty(parentName)) {
                    CustomerDistributor parent = findCustomerByUsername(parentName, true);
                    if (parent == null || parent.getListCustomerId() == null) {
                        return null;
                    }
                    List<Integer> arrs = new ArrayList<>();
                    for (String str : parent.getListCustomerId().split(",")) {
                        try {
                            arrs.add(Integer.parseInt(str));
                        } catch (Exception e) {
                        }
                    }
                    if (!arrs.isEmpty()) {
                        cr.add(Restrictions.in("id", arrs));
                    }
                }
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("userName"), "userName"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
                list = cr.list();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    public void pageData(CustomerPagination customerPagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c");
                cr.add(Restrictions.and(Restrictions.eq("isDelete", false), Restrictions.eq("isActive", true), Restrictions.not(Restrictions.eq("id", 1))));
                List<String> listKeywords = customerPagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(customerPagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + customerPagination.getSearchString() + "%'"));
                }
                cr.add(disj);
                cr.setProjection(Projections.rowCount());
                customerPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.createAlias("c.customerByParentId", "parentId", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.customerByCustomerId", "customerId", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.rankNows", "rankNow", JoinType.LEFT_OUTER_JOIN);
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("userName"), "userName")
                        .add(Projections.property("title"), "title")
                        .add(Projections.property("parentId.userName"), "parentName")
                        .add(Projections.property("customerId.userName"), "customerName")
                        .add(Projections.property("rankNow.levelId"), "rankNow"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
                cr.setFirstResult(customerPagination.getFirstResult());
                cr.setMaxResults(customerPagination.getDisplayPerPage());
                cr.addOrder(customerPagination.isAsc() ? Order.asc(customerPagination.getOrderColmn()) : Order.desc(customerPagination.getOrderColmn()));
                customerPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageData(com.resources.pagination.index.CustomerPagination customerPagination, Integer parentId) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c");
                cr.createAlias("c.customerByCustomerId", "customerId", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.eq("isDelete", false));
                cr.add(Restrictions.eq("customerId.id", parentId));
                cr.add(Restrictions.not(Restrictions.eq("c.id", 1)));
                cr.setProjection(Projections.rowCount());
                customerPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr = session.createCriteria(Customer.class, "c");
                cr.createAlias("c.customerByCustomerId", "customerId");
                cr.add(Restrictions.eq("isDelete", false));
                cr.add(Restrictions.eq("customerId.id", parentId));
                cr.setFirstResult(customerPagination.getFirstResult());
                cr.setMaxResults(customerPagination.getDisplayPerPage());
                cr.addOrder(customerPagination.isAsc() ? Order.asc(customerPagination.getOrderColmn()) : Order.desc(customerPagination.getOrderColmn()));
                customerPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageDataNonActive(CustomerPagination customerPagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c");
                cr.add(Restrictions.and(Restrictions.eq("isDelete", false), Restrictions.eq("isActive", false)));
                cr.add(Restrictions.not(Restrictions.eq("id", 1)));
                List<String> listKeywords = customerPagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(customerPagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + customerPagination.getSearchString() + "%'"));
                }
                cr.add(disj);
                cr.setProjection(Projections.rowCount());
                customerPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.createAlias("c.customerByParentId", "parentId", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.customerByCustomerId", "customerId", JoinType.LEFT_OUTER_JOIN);
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("userName"), "userName")
                        .add(Projections.property("title"), "title")
                        .add(Projections.property("parentId.userName"), "parentName")
                        .add(Projections.property("customerId.userName"), "customerName")
                        .add(Projections.property("peoplesIdentity"), "peoplesIdentity")
                        .add(Projections.property("mobile"), "mobile")
                        .add(Projections.property("createdOnUtc"), "createdOnUtc"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerNonActive.class));
                cr.setFirstResult(customerPagination.getFirstResult());
                cr.setMaxResults(customerPagination.getDisplayPerPage());
                cr.addOrder(customerPagination.isAsc() ? Order.asc(customerPagination.getOrderColmn()) : Order.desc(customerPagination.getOrderColmn()));
                customerPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageDataNeverUpRank(HistoryPagination pagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c");
                cr.createAlias("c.customerByParentId", "parentId", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.customerByCustomerId", "customerId", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.and(Restrictions.eq("isDelete", false),
                        Restrictions.eq("isActive", true),
                        Restrictions.sizeEq("c.customerRankCustomers", 0)));
                cr.add(Restrictions.not(Restrictions.eq("id", 1)));
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
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("userName"), "userName")
                        .add(Projections.property("parentId.userName"), "parentName")
                        .add(Projections.property("customerId.userName"), "customerName"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
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

    public Integer activeCustomer(Integer id) throws Exception {
        return activeCustomer(id, null);
    }

    public Integer activeCustomer(Integer id, Integer parentId) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result;
        try {
            if (id == null) {
                return 3;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q = session.createSQLQuery("ActiveCustomer :id,:parentId")
                    .setParameter("id", id)
                    .setParameter("parentId", parentId);
            result = (Integer) q.uniqueResult();
            trans.commit();
            return result;
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

    public Integer activeMultiCustomer(Integer[] ids) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result = 0;
        try {
            if (ArrayUtils.isEmpty(ids)) {
                return 3;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q;
            for (int i = 0; i < ids.length; i++) {
                q = session.createSQLQuery("ActiveCustomer :id,:parentId")
                        .setParameter("id", ids[i])
                        .setParameter("parentId", null);
                result = (Integer) q.uniqueResult();
                if (result != 1) {
                    trans.rollback();
                    return result;
                }
                if (i % 20 == 0) {
                    HibernateConfiguration.getInstance().flushSession(session);
                }
            }
            trans.commit();
            return result;
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

    public Integer deleteCustomer(Integer id) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result;
        try {
            if (id == null) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q = session.createSQLQuery("DeleteCustomer :id").setParameter("id", id);
            result = (Integer) q.uniqueResult();
            trans.commit();
            return result;
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

    public Integer deleteMultiCustomer(Integer[] ids) {
        Transaction trans = null;
        Session session = null;
        Integer result = 0;
        try {
            if (ArrayUtils.isEmpty(ids)) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q;
            for (int i = 0; i < ids.length; i++) {
                q = session.createSQLQuery("DeleteCustomer :id").setParameter("id", ids[i]);
                result = (Integer) q.uniqueResult();
                if (result != 1) {
                    trans.rollback();
                    return result;
                }
                if (i % 20 == 0) {
                    HibernateConfiguration.getInstance().flushSession(session);
                }
            }
            trans.commit();
            return result;
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

    public Integer resetPassword(Integer id) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result;
        try {
            if (id == null) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q = session.createSQLQuery("update Customer set password=:password where id=:id").setParameter("password", CustomFunction.md5("123456")).setParameter("id", id);
            result = q.executeUpdate();
            trans.commit();
            return result;
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

    public Integer resetMultiPassword(Integer[] ids) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result = 0;
        try {
            if (ArrayUtils.isEmpty(ids)) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q;
            for (int i = 0; i < ids.length; i++) {
                q = session.createSQLQuery("update Customer set password=:password where id=:id").setParameter("password", CustomFunction.md5("123456")).setParameter("id", ids[i]);
                result += q.executeUpdate();
                if (i % 20 == 0) {
                    HibernateConfiguration.getInstance().flushSession(session);
                }
            }
            trans.commit();
            return result;
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

    public Integer changePassword(Integer id) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result;
        try {
            if (id == null) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Query q = session.createSQLQuery("update Customer set password=:password where id=:id").setParameter("password", CustomFunction.md5("123456")).setParameter("id", id);
            result = q.executeUpdate();
            trans.commit();
            return result;
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

    public int create(CustomerNonActive customerNonActive) throws Exception {
        Transaction trans = null;
        Session session = null;
        int result = 0;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            if (customerNonActive == null) {
                return 1;
            }
            if (StringUtils.isEmpty(customerNonActive.getFirstName())
                    || StringUtils.isEmpty(customerNonActive.getLastName())
                    || StringUtils.isEmpty(customerNonActive.getTitle())
                    || StringUtils.isEmpty(customerNonActive.getParentName())
                    || StringUtils.isEmpty(customerNonActive.getCustomerName())) {
                return 2;
            }
            CustomerDistributor distributor = findCustomerByTitle(customerNonActive.getTitle());
            if (distributor != null) {
                return 3;
            }
            CustomerDistributor distributorParent = findCustomerByUsername(customerNonActive.getParentName(), true);
            if (distributorParent == null) {
                return 4;
            }
            CustomerDistributor distributorCustomer = findCustomerByUsername(customerNonActive.getCustomerName(), false);
            if (distributorCustomer == null) {
                return 5;
            }
            boolean flag = distributorParent.getListCustomerId().contains("," + distributorCustomer.getId() + ",") || Objects.equals(distributorParent.getId(), distributorCustomer.getId());
            if (!flag) {
                return 6;
            }
            Customer c = new Customer();
            c.setFirstName(StringUtils.escapeHtmlEntity(customerNonActive.getFirstName()));
            c.setLastName(StringUtils.escapeHtmlEntity(customerNonActive.getLastName()));
            c.setCustomerByParentId(new Customer(distributorParent.getId()));
            c.setCustomerByCustomerId(new Customer(distributorCustomer.getId()));
            c.setPeoplesIdentity(StringUtils.escapeHtmlEntity(customerNonActive.getPeoplesIdentity()));
            c.setMobile(StringUtils.escapeHtmlEntity(customerNonActive.getMobile()));
            c.setCustomerType(customerNonActive.getCustomerTypeId() == null ? null : new CustomerType(customerNonActive.getCustomerTypeId()));
            c.setEmail(StringUtils.escapeHtmlEntity(customerNonActive.getEmail()));
            c.setTaxCode(StringUtils.escapeHtmlEntity(customerNonActive.getTaxCode()));
            c.setBillingAddress(StringUtils.escapeHtmlEntity(customerNonActive.getBillingAddress()));
            c.setProvincialAgencies(new ProvincialAgencies(customerNonActive.getProvinceAgencyId()));
            c.setTitle(StringUtils.escapeHtmlEntity(customerNonActive.getTitle()));
            c.setPassword(CustomFunction.md5(customerNonActive.getPassword()));
            c.setGender(customerNonActive.getGender());
            c.setUserName(customerNonActive.getUserName());
            c.setLevel(distributorParent.getLevel() + 1);
            c.setListCustomerId(distributorParent.getListCustomerId() + "," + String.valueOf(distributorParent.getId()));
            c.setIsActive(customerNonActive.getIsActive());
            c.setAddress(StringUtils.escapeHtmlEntity(customerNonActive.getAddress()));
            super.create(c);
            if (customerNonActive.getIsActive() != null && customerNonActive.getIsActive()) {
                activeCustomer(c.getId());
            }
            result = 7;
            trans.commit();
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
        return result;
    }

    public int edit(CustomerNonActive cus) throws Exception {
        Transaction trans = null;
        Session session = null;
        int result = 0;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            if (cus == null) {
                throw new Exception();
            }
            Customer c = (Customer) find(cus.getId());
            if (c == null) {
                throw new Exception();
            }
            c.setPeoplesIdentity(StringUtils.escapeHtmlEntity(cus.getPeoplesIdentity()));
            c.setCustomerType(cus.getCustomerTypeId() == null ? null : new CustomerType(cus.getCustomerTypeId()));
            c.setEmail(StringUtils.escapeHtmlEntity(cus.getEmail()));
            c.setGender(cus.getGender());
            c.setMobile(StringUtils.escapeHtmlEntity(cus.getMobile()));
            c.setBillingAddress(StringUtils.escapeHtmlEntity(cus.getBillingAddress()));
            c.setTaxCode(StringUtils.escapeHtmlEntity(cus.getTaxCode()));
            c.setAddress(StringUtils.escapeHtmlEntity(cus.getAddress()));
            session.update(c);
            result = 1;
            trans.commit();
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
        return result;
    }

    public CustomerDistributor findCustomerByUsername(String userName, boolean parentType) {
        Session session = null;
        CustomerDistributor result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(RankNow.class, "r");
                cr.createAlias("r.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.and(Restrictions.eq("cus.isDelete", false), Restrictions.eq("cus.isActive", true), Restrictions.eq("cus.userName", userName)));
                cr.add(Restrictions.not(Restrictions.eq("cus.id", 1)));
                if (parentType) {
                    cr.add(Restrictions.or(Restrictions.eq("r.userId", 0), Restrictions.eq("r.userId1", 0)));
                }
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("cus.id"), "id")
                        .add(Projections.property("cus.listCustomerId"), "listCustomerId")
                        .add(Projections.property("cus.level"), "level"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
                result = (CustomerDistributor) cr.uniqueResult();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public Customer findCustomerByUsername(String userName) {
        Session session = null;
        Customer result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c");
                cr.add(Restrictions.and(Restrictions.eq("c.isDelete", false), Restrictions.eq("c.isActive", true), Restrictions.eq("c.userName", userName)));
                result = (Customer) cr.uniqueResult();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public CustomerDistributor findCustomerByTitle(String title) {
        Session session = null;
        CustomerDistributor result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c")
                        .add(Restrictions.and(Restrictions.eq("isDelete", false), Restrictions.eq("title", title)))
                        .setProjection(Projections.projectionList()
                                .add(Projections.property("id"), "id"));
                cr.setResultTransformer(Transformers.aliasToBean(CustomerDistributor.class));
                result = (CustomerDistributor) cr.uniqueResult();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public CustomerNonActive findDistributorById(Integer id) {
        Session session = null;
        CustomerNonActive result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(Customer.class, "c")
                        .add(Restrictions.and(Restrictions.eq("isDelete", false), Restrictions.eq("id", id)));
                cr.createAlias("c.customerType", "cusType", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.provincialAgencies", "provinc", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.customerByParentId", "customerParent", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.customerByCustomerId", "customerCustomer", JoinType.LEFT_OUTER_JOIN);
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("firstName"), "firstName")
                        .add(Projections.property("lastName"), "lastName")
                        .add(Projections.property("userName"), "userName")
                        .add(Projections.property("peoplesIdentity"), "peoplesIdentity")
                        .add(Projections.property("mobile"), "mobile")
                        .add(Projections.property("cusType.id"), "customerTypeId")
                        .add(Projections.property("cusType.name"), "customerTypeName")
                        .add(Projections.property("provinc.id"), "provinceAgencyId")
                        .add(Projections.property("provinc.name"), "provinceAgencyName")
                        .add(Projections.property("email"), "email")
                        .add(Projections.property("gender"), "gender")
                        .add(Projections.property("customerParent.id"), "parentId")
                        .add(Projections.property("customerParent.userName"), "parentName")
                        .add(Projections.property("customerCustomer.id"), "customerId")
                        .add(Projections.property("customerCustomer.userName"), "customerName")
                        .add(Projections.property("billingAddress"), "billingAddress")
                        .add(Projections.property("createdOnUtc"), "createdOnUtc")
                        .add(Projections.property("taxCode"), "taxCode")
                        .add(Projections.property("title"), "title")
                        .add(Projections.property("address"), "address"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerNonActive.class));
                result = (CustomerNonActive) cr.uniqueResult();
            }
        } catch (Exception e) {
            Logger.getLogger(entityClass.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public Integer findParentIdFromChildrenId(Integer children) throws Exception {
        Session session = null;
        Integer parentId = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Criteria cr = session.createCriteria(Customer.class, "c");
            cr.add(Restrictions.eq("id", children));
            cr.add(Restrictions.eq("isDelete", false));
            cr.add(Restrictions.not(Restrictions.eq("id", 0)));
            cr.createAlias("c.customerByParentId", "parent", JoinType.LEFT_OUTER_JOIN);
            cr.setProjection(Projections.projectionList().add(Projections.property("parent.id")));
            parentId = (Integer) cr.uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return parentId;
    }

    public Customer login(Customer cus) throws Exception {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Criteria cr = session.createCriteria(Customer.class);
            cr.add(Restrictions.or(Restrictions.eq("userName", cus.getUserName()), Restrictions.eq("title", cus.getUserName())));
            cr.add(Restrictions.not(Restrictions.eq("id", 0)));
            cr.add(Restrictions.eq("password", cus.getPassword()));
            cr.add(Restrictions.eq("isDelete", false));
            cus = (Customer) cr.uniqueResult();
        } catch (Exception e) {
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return cus;
    }

    public List<CustomerFromTotalParent> totalParent(String listCustomerId) throws Exception {
        Session session = null;
        Transaction trans = null;
        List<CustomerFromTotalParent> result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                trans = session.beginTransaction();
                Query q = session.getNamedQuery("TotalParent");
                q.setParameter("listCustomerId", listCustomerId);
                result = q.list();
                trans.commit();
            }
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public String getNewestUser() {
        Session session = null;
        String result = "";
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createQuery("select userName from Customer where isDelete=0 order by createdOnUtc DESC");
                q.setMaxResults(1);
                result = (String) q.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
    }

    public String createUserName() {
        StringBuilder sB = new StringBuilder();
        Random random = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyhhss");
        sB.append(sdf.format(new Date())).append(String.valueOf(random.nextInt(9999 - 1001) + 1001));
        return sB.toString();
    }
}
