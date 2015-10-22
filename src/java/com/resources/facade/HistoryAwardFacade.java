package com.resources.facade;

import com.resources.bean.ExcelFile;
import com.resources.bean.HistoryAward;
import com.resources.entity.CustomerRankCustomer;
import com.resources.entity.HistoryAwards;
import com.resources.entity.Module;
import com.resources.function.CustomFunction;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.pagination.admin.ReportPagination;
import com.resources.utils.ArrayUtils;
import com.resources.utils.StringUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

public class HistoryAwardFacade extends AbstractFacade {

    public HistoryAwardFacade() {
        super(CustomerRankCustomer.class);
    }

    public void pageData(HistoryPagination historyPagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(HistoryAwards.class, "a");
                cr.createAlias("a.customer", "cus");

                cr.add(Restrictions.and(Restrictions.eq("a.isDeleted", false), Restrictions.eq("cus.isDelete", false), Restrictions.eq("cus.isActive", true)));
                List<String> listKeywords = historyPagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(historyPagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + historyPagination.getSearchString() + "%'"));
                }
                cr.add(disj);

                cr.setProjection(Projections.rowCount());
                historyPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("name"), "name")
                        .add(Projections.property("cus.userName"), "userName")
                        .add(Projections.property("pricePv"), "pricePv")
                        .add(Projections.property("dateCreated"), "dateCreated"))
                        .setResultTransformer(Transformers.aliasToBean(HistoryAward.class));
                cr.setFirstResult(historyPagination.getFirstResult());
                cr.setMaxResults(historyPagination.getDisplayPerPage());
                cr.addOrder(historyPagination.isAsc() ? Order.asc(historyPagination.getOrderColmn()) : Order.desc(historyPagination.getOrderColmn()));

                historyPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageData(ReportPagination pagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(HistoryAwards.class, "h");
                cr.createAlias("h.customer", "cus", JoinType.LEFT_OUTER_JOIN);

                cr.add(Restrictions.and(Restrictions.eq("h.isDeleted", false), Restrictions.eq("cus.isDelete", false), Restrictions.eq("cus.isActive", true)));
                if (pagination.getMonth() != null && pagination.getYear() != null) {
                    cr.add(Restrictions.sqlRestriction("MONTH(DateCreated)=" + pagination.getMonth()));
                }

                if (pagination.getYear() != null) {
                    cr.add(Restrictions.sqlRestriction("YEAR(DateCreated)=" + pagination.getYear()));
                }

                List<String> listKeywords = pagination.getKeywords();
                Disjunction disj = Restrictions.disjunction();
                for (String k : listKeywords) {
                    if (StringUtils.isEmpty(pagination.getSearchString())) {
                        break;
                    }
                    disj.add(Restrictions.sqlRestriction("CAST(" + k + " AS VARCHAR) like '%" + pagination.getSearchString() + "%'"));
                }
                cr.add(disj);

                String queryString = "select count(*) from (select h.customerId from HistoryAwards h join Customer c "
                        + "on h.CustomerId=c.id "
                        + "where h.isDeleted=0";
                if (pagination.getMonth() != null && pagination.getYear() != null) {
                    queryString += " and MONTH(h.dateCreated)=" + pagination.getMonth();
                }

                if (pagination.getYear() != null) {
                    queryString += " and YEAR(h.dateCreated)=" + pagination.getYear();
                }
                queryString += " and c.IsDelete=0 and c.IsActive=1 "
                        + "group by h.customerId)z";
                Query q = session.createSQLQuery(queryString);

                pagination.setTotalResult((Integer) q.uniqueResult());

                cr.setProjection(Projections.projectionList()
                        .add(Projections.sum("pricePv"), "pricePv")
                        .add(Projections.groupProperty("cus.id"), "cusId")
                        .add(Projections.groupProperty("cus.userName"), "userName")
                        .add(Projections.groupProperty("cus.firstName"), "firstName")
                        .add(Projections.groupProperty("cus.lastName"), "lastName")
                        .add(Projections.groupProperty("cus.lastName"), "lastName")
                        .add(Projections.groupProperty("cus.lastName"), "lastName"));
                cr.setResultTransformer(Transformers.aliasToBean(HistoryAward.class));
                cr.setFirstResult(pagination.getFirstResult());
                cr.setMaxResults(pagination.getDisplayPerPage());
                cr.addOrder(pagination.isAsc() ? Order.asc(pagination.getOrderColmn()) : Order.desc(pagination.getOrderColmn()));

                pagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }
    public void setExportFile(ExcelFile file, Integer month, Integer year) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createSQLQuery("select ha.customerId,c.firstName,c.lastName,c.userName,c.peoplesIdentity,c.email,c.mobile,c.address,c.taxCode,"
                        + "c.billingAddress,isnull(t1.total,0) total1, "
                        + "isnull(t2.total,0) total2,isnull(t3.total,0) total3, "
                        + "isnull(t4.total,0) total4,isnull(t5.total,0) total5, isnull(sum(ha.PricePv),0) total6 "
                        + "from HistoryAwards ha left join Customer c "
                        + "on ha.CustomerId=c.id "
                        + "left join (select CustomerId,sum(PricePv) as total from HistoryAwards where Month(DateCreated)= :month and Year(DateCreated)= :year and IsDeleted=0 and CheckAwardkId=1 group by CustomerId) t1 "
                        + "on ha.CustomerId=t1.CustomerId "
                        + "left join (select CustomerId,sum(PricePv) as total from HistoryAwards where Month(DateCreated)= :month and Year(DateCreated)= :year and IsDeleted=0 and CheckAwardkId=4 group by CustomerId) t2 "
                        + "on ha.CustomerId=t2.CustomerId "
                        + "left join (select CustomerId,sum(PricePv) as total from HistoryAwards where Month(DateCreated)= :month and Year(DateCreated)= :year and IsDeleted=0 and CheckAwardkId=13 group by CustomerId) t3 "
                        + "on ha.CustomerId=t3.CustomerId "
                        + "left join (select CustomerId,sum(PricePv) as total from HistoryAwards where Month(DateCreated)= :month and Year(DateCreated)= :year and IsDeleted=0 and CheckAwardkId=28 group by CustomerId) t4 "
                        + "on ha.CustomerId=t4.CustomerId "
                        + "left join (select CustomerId,sum(PricePv) as total from HistoryAwards where Month(DateCreated)= :month and Year(DateCreated)= :year and IsDeleted=0 and CheckAwardkId=29 group by CustomerId) t5 "
                        + "on ha.CustomerId=t5.CustomerId "
                        + "where ha.IsDeleted=0 and c.IsDelete=0 and c.IsActive=1 and Month(ha.DateCreated)= :month and Year(ha.DateCreated)= :year "
                        + "group by ha.CustomerId,c.firstName,c.lastName,c.Username,c.peoplesIdentity,c.Email,c.mobile,c.Address,c.TaxCode,c.BillingAddress,t1.total,t2.total,t3.total,t4.total,t5.total")
                        .addScalar("customerId", IntegerType.INSTANCE)
                        .addScalar("firstName", StringType.INSTANCE)
                        .addScalar("lastName", StringType.INSTANCE)
                        .addScalar("userName", StringType.INSTANCE)
                        .addScalar("peoplesIdentity", StringType.INSTANCE)
                        .addScalar("email", StringType.INSTANCE)
                        .addScalar("mobile", StringType.INSTANCE)
                        .addScalar("address", StringType.INSTANCE)
                        .addScalar("taxCode", StringType.INSTANCE)
                        .addScalar("billingAddress", StringType.INSTANCE)
                        .addScalar("total1", BigDecimalType.INSTANCE)
                        .addScalar("total2", BigDecimalType.INSTANCE)
                        .addScalar("total3", BigDecimalType.INSTANCE)
                        .addScalar("total4", BigDecimalType.INSTANCE)
                        .addScalar("total5", BigDecimalType.INSTANCE)
                        .addScalar("total6", BigDecimalType.INSTANCE);
                q.setParameter("month", month);
                q.setParameter("year", year);
                q.list();
                List<String> header = new ArrayList();
                header.add("ID");
                header.add("Họ");
                header.add("Tên");
                header.add("Tên đăng nhập");
                header.add("CMND");
                header.add("Email");
                header.add("SĐT");
                header.add("Địa chỉ liên hệ");
                header.add("Số TK ngân hàng");
                header.add("Địa chỉ ngân hàng");
                header.add("Tổng thưởng mã rơi");
                header.add("Tổng thưởng trực tiếp 2%");
                header.add("Tổng thưởng cân cặp");
                header.add("Tổng thưởng hoa hồng đầu tư");
                header.add("Tổng thưởng lên cấp");
                header.add("Tổng thưởng");
                file.setTitles(header);
                List rs = q.list();
                for (Object rows : rs) {
                    Object[] row = (Object[]) rows;
                    row[10] = CustomFunction.formatCurrency((BigDecimal) row[10]);
                    row[11] = CustomFunction.formatCurrency((BigDecimal) row[11]);
                    row[12] = CustomFunction.formatCurrency((BigDecimal) row[12]);
                    row[13] = CustomFunction.formatCurrency((BigDecimal) row[13]);
                    row[14] = CustomFunction.formatCurrency((BigDecimal) row[14]);
                    row[15] = CustomFunction.formatCurrency((BigDecimal) row[15]);
                }
                file.setContents(rs);
                file.setFileName("Thong ke hoa hong NPP " + month + "-" + year);
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public List<HistoryAward> pageData(HistoryPagination historyPagination, int cusId, int checkAwardId, int month, int year) throws Exception {
        Session session = null;
        List<HistoryAward> list = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(HistoryAwards.class, "ha");
                cr.createAlias("ha.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("ha.checkAwards", "a", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.eq("isDeleted", false))
                        .add(Restrictions.eq("cus.isDelete", false))
                        .add(Restrictions.eq("cus.isActive", true))
                        .add(Restrictions.sqlRestriction("MONTH(dateCreated)=?", month, IntegerType.INSTANCE))
                        .add(Restrictions.sqlRestriction("YEAR(dateCreated)=?", year, IntegerType.INSTANCE))
                        .add(Restrictions.eq("a.id", checkAwardId));
                cr.setProjection(Projections.rowCount());
                historyPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"))
                        .add(Projections.property("name"))
                        .add(Projections.property("price"))
                        .add(Projections.property("pricePv"))
                        .add(Projections.property("dateCreated")));
                cr.setFirstResult(historyPagination.getFirstResult());
                cr.setMaxResults(historyPagination.getDisplayPerPage());
                cr.addOrder(historyPagination.isAsc() ? Order.asc(historyPagination.getOrderColmn()) : Order.desc(historyPagination.getOrderColmn()));
                historyPagination.setDisplayList(cr.list());
                return cr.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    public LinkedHashMap reportAllAwardByMonth(int year) {
        Session session = null;
        LinkedHashMap<String, Object[]> report = new LinkedHashMap();
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Calendar now = Calendar.getInstance();
            int currentMonth = now.get(Calendar.MONTH);
            if (session != null) {
                Query q = session.createSQLQuery("ReportAllAwardByMonth :year").setParameter("year", year);
                String key;
                for (Object rows : q.list()) {
                    Object[] row = (Object[]) rows;
                    for (int i = currentMonth + 2; i <= 12; i++) {
                        row[i] = null;
                    }
                    switch ((Integer) row[0]) {
                        case 1: {
                            key = "Thưởng mã rơi";
                            break;
                        }
                        case 29: {
                            key = "Thưởng lên cấp";
                            break;
                        }
                        case 4: {
                            key = "Thưởng trực tiếp";
                            break;
                        }
                        case 13: {
                            key = "Thưởng cân cặp";
                            break;
                        }
                        case 28: {
                            key = "Thưởng đầu tư";
                            break;
                        }
                        default: {
                            key = "Thưởng lên cấp";
                            break;
                        }
                    }
                    report.put(key, ArrayUtils.removeItem(row, 0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return report;
    }

    public Map reportAllComissionByMonth(int year) {
        Session session = null;
        LinkedHashMap<String, Object[]> report = new LinkedHashMap();
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Calendar now = Calendar.getInstance();
            int currentMonth = now.get(Calendar.MONTH);
            if (session != null) {
                Query q = session.createSQLQuery("ReportAllComissionByMonth :year").setParameter("year", year);
                for (Object rows : q.list()) {
                    Object[] row = (Object[]) rows;
                    for (int i = currentMonth + 2; i <= 12; i++) {
                        row[i] = null;
                    }
                    String key;
                    switch ((Integer) row[0]) {
                        case 0: {
                            key = "Tổng Thu";
                            break;
                        }
                        case 1: {
                            key = "Tổng Chi";
                            break;
                        }
                        case 2: {
                            key = "Lãi Xuất";
                            break;
                        }
                        case 3: {
                            key = "Chi/Thu(%)";
                            break;
                        }
                        default: {
                            key = "Error";
                            break;
                        }
                    }
                    report.put(key, ArrayUtils.removeItem(row, 0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return report;
    }

    public Integer countNewUserInCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return countNewUser(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    public Integer countNewUserInCurrentYear() {
        Calendar c = Calendar.getInstance();
        return countNewUser(null, c.get(Calendar.YEAR));
    }

    public Integer countNewUser(Integer month, Integer year) {
        Session session = null;
        Integer result = 0;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Query q = session.createSQLQuery("CountNewUser :month,:year")
                    .setParameter("month", month)
                    .setParameter("year", year);
            result = (Integer) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public BigDecimal getTotalInInCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return getTotalIn(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR), null);
    }

    public BigDecimal getTotalInInCurrentYear() {
        Calendar c = Calendar.getInstance();
        return getTotalIn(null, c.get(Calendar.YEAR), null);
    }

    public BigDecimal getTotalIn(Integer month, Integer year, Integer cusid) {
        Session session = null;
        BigDecimal result = BigDecimal.valueOf(0);
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Query q = session.createSQLQuery("GetTotalIn :month,:year,:cusid")
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .setParameter("cusid", cusid);
            result = (BigDecimal) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public List reportAllTotalAwardInCurrentYear() {
        Calendar c = Calendar.getInstance();
        return reportAllTotalAwardByMonth(null, c.get(Calendar.YEAR));
    }

    public List reportAllTotalAwardByMonth(Integer month, Integer year) {
        Session session = null;
        List result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Query q = session.createSQLQuery("ReportAllTotalAwardByMonth :month,:year")
                    .addScalar("Name", StringType.INSTANCE)
                    .addScalar("Total", BigDecimalType.INSTANCE)
                    .setParameter("month", month)
                    .setParameter("year", year);
            result = q.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public BigDecimal getTotalOutInCurrentMonth() {
        Calendar c = Calendar.getInstance();
        return getTotalOut(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR), null);
    }

    public BigDecimal getTotalOutInCurrentYear() {
        Calendar c = Calendar.getInstance();
        return getTotalOut(null, c.get(Calendar.YEAR), null);
    }

    public BigDecimal getTotalOut(Integer month, Integer year, Integer cusid) {
        Session session = null;
        BigDecimal result = BigDecimal.valueOf(0);
        try {
            session = HibernateConfiguration.getInstance().openSession();
            Query q = session.createSQLQuery("GetTotalOut :month,:year,:cusid")
                    .setParameter("month", month)
                    .setParameter("year", year)
                    .setParameter("cusid", cusid);
            result = (BigDecimal) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public void pageData(com.resources.pagination.index.ReportPagination reportPagination, Integer cusId) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(HistoryAwards.class, "a");
                cr.createAlias("a.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("a.checkAwards", "ca", JoinType.LEFT_OUTER_JOIN);

                cr.add(Restrictions.eq("a.isDeleted", false));
                cr.add(Restrictions.eq("cus.isDelete", false));
                cr.add(Restrictions.eq("cus.isActive", true));
                if (reportPagination.getMonth() != null && reportPagination.getYear() != null) {
                    cr.add(Restrictions.sqlRestriction("MONTH(DateCreated)=" + reportPagination.getMonth()));
                }

                if (reportPagination.getYear() != null) {
                    cr.add(Restrictions.sqlRestriction("YEAR(DateCreated)=" + reportPagination.getYear()));
                }

                cr.add(Restrictions.eq("cus.id", cusId));
                cr.add(Restrictions.eq("ca.id", reportPagination.getAwardType()));
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("name"), "name")
                        .add(Projections.property("pricePv"), "pricePv")
                        .add(Projections.property("dateCreated"), "dateCreated"))
                        .setResultTransformer(Transformers.aliasToBean(HistoryAward.class));
                cr.addOrder(reportPagination.isAsc() ? Order.asc(reportPagination.getOrderColmn()) : Order.desc(reportPagination.getOrderColmn()));

                reportPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public List getTop5AwardInMonth() {
        Calendar c = Calendar.getInstance();
        return getTop5Award(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    public List getTop5AwardInYear() {
        Calendar c = Calendar.getInstance();
        return getTop5Award(null, c.get(Calendar.YEAR));
    }

    public List getTop5Award(Integer month, Integer year) {
        Session session = null;
        List result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createSQLQuery("GetTop5Award :month,:year")
                        .addScalar("Name", StringType.INSTANCE)
                        .addScalar("UserName", StringType.INSTANCE)
                        .addScalar("PricePv", BigDecimalType.INSTANCE);
                q.setParameter("month", month);
                q.setParameter("year", year);
                result = q.list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public List findAllHistoryAwardYear() {
        Session session = null;
        List result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createSQLQuery("select YEAR(dateCreated) from HistoryAwards group by YEAR(dateCreated)");
                result = q.list();
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
}
