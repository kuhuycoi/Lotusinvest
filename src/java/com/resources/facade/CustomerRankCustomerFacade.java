package com.resources.facade;

import com.resources.bean.CustomerFromTotalParent;
import com.resources.bean.HistoryCustomerRank;
import com.resources.entity.CheckAwards;
import com.resources.entity.Customer;
import com.resources.entity.CustomerRankCustomer;
import com.resources.entity.HistoryAwards;
import com.resources.entity.Module;
import com.resources.entity.PinSys;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.utils.StringUtils;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
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
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.StringType;

public class CustomerRankCustomerFacade extends AbstractFacade {

    public CustomerRankCustomerFacade() {
        super(CustomerRankCustomer.class);
    }

    public void pageData(HistoryPagination historyPagination) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(CustomerRankCustomer.class, "c");
                cr.createAlias("c.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.provincialAgencies", "pA", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.and(Restrictions.eq("c.isDeleted", false), Restrictions.eq("cus.isDelete", false), Restrictions.eq("cus.isActive", true)));

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
                        .add(Projections.property("cus.userName"), "userName")
                        .add(Projections.property("cus.lastName"), "lastName")
                        .add(Projections.property("cus.firstName"), "firstName")
                        .add(Projections.property("pricePv"), "pricePv")
                        .add(Projections.property("dateCreated"), "dateCreated")
                        .add(Projections.property("pA.name"), "provincialAgencyName"))
                        .setResultTransformer(Transformers.aliasToBean(HistoryCustomerRank.class));
                cr.setFirstResult(historyPagination.getFirstResult());
                cr.setMaxResults(historyPagination.getDisplayPerPage());
                cr.addOrder(historyPagination.isAsc() ? Order.asc(historyPagination.getOrderColmn()) : Order.desc(historyPagination.getOrderColmn()));
                historyPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public void pageData(com.resources.pagination.index.HistoryPagination historyPagination, Integer cusId) {
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(CustomerRankCustomer.class, "c");
                cr.createAlias("c.customer", "cus", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.provincialAgencies", "pA", JoinType.LEFT_OUTER_JOIN);
                cr.createAlias("c.rankCustomes", "rank", JoinType.LEFT_OUTER_JOIN);
                cr.add(Restrictions.and(Restrictions.eq("c.isDeleted", false), Restrictions.eq("cus.isDelete", false), Restrictions.eq("cus.isActive", true)));
                cr.add(Restrictions.eq("cus.id", cusId));
                cr.setProjection(Projections.rowCount());
                historyPagination.setTotalResult(((Long) cr.uniqueResult()).intValue());
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("rank.name"), "rankName")
                        .add(Projections.property("pricePv"), "pricePv")
                        .add(Projections.property("pA.name"), "provincialAgencyName")
                        .add(Projections.property("dateCreated"), "dateCreated"))
                        .setResultTransformer(Transformers.aliasToBean(HistoryCustomerRank.class));
                cr.setFirstResult(historyPagination.getFirstResult());
                cr.setMaxResults(historyPagination.getDisplayPerPage());
                cr.addOrder(historyPagination.isAsc() ? Order.asc(historyPagination.getOrderColmn()) : Order.desc(historyPagination.getOrderColmn()));
                historyPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    public int depositPv(String userName, Integer rankCustomerId, Integer multipleGrateful) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result = 0;
        int maxCircle = 112;
        try {
            if (userName == null || rankCustomerId == null) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            CustomerFacade cFacade = new CustomerFacade();
            Customer c = cFacade.findCustomerByUsername(userName);

            Query q = session.createSQLQuery("DepositPV :userName,:rankCustomerId").setParameter("userName", userName).setParameter("rankCustomerId", rankCustomerId);

            String[] listParent = c.getListCustomerId() == null ? null : c.getListCustomerId().split(",");
            result = (Integer) q.uniqueResult();
            if (result == 2) {
                q = session.createSQLQuery("upleveltrian2 :cusID,:multiple")
                        .setParameter("cusID", c.getId()).
                        setParameter("multiple", multipleGrateful);
                q.executeUpdate();

                StringBuilder sB = new StringBuilder();
                sB.append("INSERT INTO HistoryAwards(name,price,customerId,checkAwardkId,pricePv,customerRankId) VALUES");
                for (int i = (listParent.length > 9) ? listParent.length - 9 : 0; i < listParent.length; i++) {
                    if (i == listParent.length - 1) {
                        sB.append("(:name,:price,").append(listParent[i]).append(",1,:pricePv,:customerRankId)");
                    } else {
                        sB.append("(:name,:price,").append(listParent[i]).append(",1,:pricePv,:customerRankId),");
                    }
                }
                q = session.createSQLQuery(sB.toString())
                        .setParameter("name", "Thưởng mã rơi từ NPP: " + userName)
                        .setParameter("price", new BigDecimal(80.0000))
                        .setParameter("pricePv", new BigDecimal(8.0000))
                        .setParameter("customerRankId", rankCustomerId);
                q.executeUpdate();
                System.out.println("List" + c.getListCustomerId());
                q = session.getNamedQuery("TotalParent");
                q.setParameter("listCustomerId", c.getListCustomerId());
                List<CustomerFromTotalParent> customerFromTotalParent = q.list();
                for (CustomerFromTotalParent cus : customerFromTotalParent) {
                    BigDecimal pv;
                    if (cus.getCircle() != null) {
                        if (cus.getPVANow() == null && cus.getCircle() < maxCircle) {
                            if (!cus.getIsPay()) {
                                if (cus.getCircle() + cus.getCircleNumber() > 5) {
                                    //Có thể có giá trị âm
                                    pv = BigDecimal.valueOf((5 - cus.getCircleNumber()) * 200 + (cus.getCircle() - 5) * 50);
                                } else {
                                    pv = BigDecimal.valueOf(cus.getCircle() * 200);
                                }
                            } else {
                                pv = BigDecimal.valueOf(cus.getCircle() * 50);
                            }

                            if (pv.compareTo(BigDecimal.ZERO) > 0) {
                                HistoryAwards ha = new HistoryAwards();
                                ha.setCustomer(new Customer(cus.getID()));
                                System.out.println("ID:" + cus.getID());
                                ha.setPricePv(pv);
                                ha.setPrice(pv.multiply(BigDecimal.valueOf(10)));
                                ha.setName("Thưởng cân cặp từ nhà phân phối: " + userName);
                                ha.setCustomerRankId(rankCustomerId);
                                ha.setCheckAwards(new CheckAwards(13));
                                ha.setCurrentCycle(cus.getCircle());
                                ha.setCycleNumber(cus.getCircle());
                                ha.setPvanow(cus.getRnPricePVUser().add(cus.getPVMonth()));

                                ha.setPvbnow(cus.getRnPricePVUser1().add(cus.getPVMonth1()));
                                session.save(ha);
                            }
                        } else if (cus.getPVANow() != null) {
                            int circleCheck = cus.getCircle() + cus.getCircleOld();
                            int multiple = 0;

                            if (circleCheck < maxCircle) {
                                multiple = circleCheck - cus.getCurrentCircle();
                                if (!cus.getIsPay()) {
                                    if (cus.getCircleNumber() + multiple > 5) {
                                        pv = BigDecimal.valueOf((5 - cus.getCircleNumber()) * 200 + ((cus.getCircleNumber() + multiple) - 5) * 50);
                                    } else {
                                        pv = BigDecimal.valueOf(multiple * 200);
                                    }
                                } else {
                                    pv = BigDecimal.valueOf(multiple * 50);
                                }
                            } else {
                                pv = BigDecimal.valueOf((maxCircle - cus.getCurrentCircle()) * 50);
                                if (!pv.equals(0)) {
                                    circleCheck = maxCircle;
                                }

                            }
                            if (pv.compareTo(BigDecimal.ZERO) > 0) {
                                HistoryAwards ha = new HistoryAwards();
                                ha.setCustomer(new Customer(cus.getID()));
                                ha.setPricePv(pv);
                                ha.setPrice(pv.multiply(BigDecimal.valueOf(10)));
                                ha.setName("Thưởng cân cặp: " + userName);
                                ha.setCustomerRankId(rankCustomerId);
                                ha.setCycleNumber(multiple);
                                ha.setCurrentCycle(circleCheck);
                                ha.setCheckAwards(new CheckAwards(13));
                                ha.setPvanow(cus.getRnPricePVUser().add(cus.getPVMonth()));
                                ha.setPvbnow(cus.getRnPricePVUser1().add(cus.getPVMonth1()));
                                session.save(ha);
                            }
                        }
                    }
                    if (cus.getCircle() + cus.getCircleOld() >= 20) {
                        HistoryAwards ha;
                        if (cus.getAward10() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(10000));
                            ha.setPricePv(BigDecimal.valueOf(1000));
                            ha.setName("Thưởng lên cấp 20 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award10=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 50 && cus.getAward20() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(15000));
                            ha.setPricePv(BigDecimal.valueOf(1500));
                            ha.setName("Thưởng lên cấp 50 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award20=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 80 && cus.getAward30() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(15000));
                            ha.setPricePv(BigDecimal.valueOf(1500));
                            ha.setName("Thưởng lên cấp 80 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award30=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 112 && cus.getAward39() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(30000));
                            ha.setPricePv(BigDecimal.valueOf(3000));
                            ha.setName("Thưởng lên cấp 112 cặp");
                            ha.setIsDeleted(Boolean.FALSE);
                            ha.setIsShow(Boolean.TRUE);
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award39=1,activeRank=2,levelId=:level_id,level=2 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.setParameter("level_id", "Giamsat");
                            q.executeUpdate();
                        }
                    }
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

    public int depositPv(Integer cusId, String pinNumber) throws Exception {
        Transaction trans = null;
        Session session = null;
        Integer result;
        int maxCircle = 112;
        int multipleGrateful = 1;
        try {
            if (cusId == null || pinNumber == null) {
                return 0;
            }
            session = HibernateConfiguration.getInstance().openSession();
            trans = session.beginTransaction();
            Customer c = (Customer) (new CustomerFacade().find(cusId));
            if (c == null) {
                return 0;
            }
            String userName = c.getUserName();
            PinSys pin;
            PinSysFacade pFacade = new PinSysFacade();
            pin = pFacade.find(pinNumber);
            if (pin == null || pin.getIsUsed()) {
                return 3;
            }
            Integer rankCustomerId = pin.getPinType().getId();
            Query q = session.createSQLQuery("DepositPV :userName,:rankCustomerId").setParameter("userName", userName).setParameter("rankCustomerId", rankCustomerId);

            String[] listParent = c.getListCustomerId().split(",");
            result = (Integer) q.uniqueResult();
            if (result == 2) {
                pin.setIsUsed(true);
                pin.setUsedCustomer(c);
                pin.setUsedDate(new Date());
                session.update(pin);

                q = session.createSQLQuery("upleveltrian2 :cusID,:multiple")
                        .setParameter("cusID", c.getId()).
                        setParameter("multiple", multipleGrateful);
                q.executeUpdate();

                StringBuilder sB = new StringBuilder();
                sB.append("INSERT INTO HistoryAwards(name,price,customerId,checkAwardkId,pricePv,customerRankId) VALUES");
                for (int i = (listParent.length > 9) ? listParent.length - 9 : 0; i < listParent.length; i++) {
                    if (i == listParent.length - 1) {
                        sB.append("(:name,:price,").append(listParent[i]).append(",1,:pricePv,:customerRankId)");
                    } else {
                        sB.append("(:name,:price,").append(listParent[i]).append(",1,:pricePv,:customerRankId),");
                    }
                }
                q = session.createSQLQuery(sB.toString())
                        .setParameter("name", "Thưởng mã rơi từ NPP: " + userName)
                        .setParameter("price", new BigDecimal(80.0000))
                        .setParameter("pricePv", new BigDecimal(8.0000))
                        .setParameter("customerRankId", rankCustomerId);
                q.executeUpdate();
                System.out.println("List" + c.getListCustomerId());
                q = session.getNamedQuery("TotalParent");
                q.setParameter("listCustomerId", c.getListCustomerId());
                List<CustomerFromTotalParent> customerFromTotalParent = q.list();
                for (CustomerFromTotalParent cus : customerFromTotalParent) {
                    BigDecimal pv;
                    if (cus.getCircle() != null) {
                        if (cus.getPVANow() == null && cus.getCircle() < maxCircle) {
                            if (!cus.getIsPay()) {
                                if (cus.getCircle() + cus.getCircleNumber() > 5) {
                                    //Có thể có giá trị âm
                                    pv = BigDecimal.valueOf((5 - cus.getCircleNumber()) * 200 + (cus.getCircle() - 5) * 50);
                                } else {
                                    pv = BigDecimal.valueOf(cus.getCircle() * 200);
                                }
                            } else {
                                pv = BigDecimal.valueOf(cus.getCircle() * 50);
                            }

                            if (pv.compareTo(BigDecimal.ZERO) > 0) {
                                HistoryAwards ha = new HistoryAwards();
                                ha.setCustomer(new Customer(cus.getID()));
                                System.out.println("ID:" + cus.getID());
                                ha.setPricePv(pv);
                                ha.setPrice(pv.multiply(BigDecimal.valueOf(10)));
                                ha.setName("Thưởng cân cặp từ nhà phân phối: " + userName);
                                ha.setCustomerRankId(rankCustomerId);
                                ha.setCheckAwards(new CheckAwards(13));
                                ha.setCurrentCycle(cus.getCircle());
                                ha.setCycleNumber(cus.getCircle());
                                ha.setPvanow(cus.getRnPricePVUser().add(cus.getPVMonth()));

                                ha.setPvbnow(cus.getRnPricePVUser1().add(cus.getPVMonth1()));
                                session.save(ha);
                            }
                        } else if (cus.getPVANow() != null) {
                            int circleCheck = cus.getCircle() + cus.getCircleOld();
                            int multiple = 0;

                            if (circleCheck < maxCircle) {
                                multiple = circleCheck - cus.getCurrentCircle();
                                if (!cus.getIsPay()) {
                                    if (cus.getCircleNumber() + multiple > 5) {
                                        pv = BigDecimal.valueOf((5 - cus.getCircleNumber()) * 200 + ((cus.getCircleNumber() + multiple) - 5) * 50);
                                    } else {
                                        pv = BigDecimal.valueOf(multiple * 200);
                                    }
                                } else {
                                    pv = BigDecimal.valueOf(multiple * 50);
                                }
                            } else {
                                pv = BigDecimal.valueOf((maxCircle - cus.getCurrentCircle()) * 50);
                                if (!pv.equals(0)) {
                                    circleCheck = maxCircle;
                                }

                            }
                            if (pv.compareTo(BigDecimal.ZERO) > 0) {
                                HistoryAwards ha = new HistoryAwards();
                                ha.setCustomer(new Customer(cus.getID()));
                                ha.setPricePv(pv);
                                ha.setPrice(pv.multiply(BigDecimal.valueOf(10)));
                                ha.setName("Thưởng cân cặp: " + userName);
                                ha.setCustomerRankId(rankCustomerId);
                                ha.setCycleNumber(multiple);
                                ha.setCurrentCycle(circleCheck);
                                ha.setCheckAwards(new CheckAwards(13));
                                ha.setPvanow(cus.getRnPricePVUser().add(cus.getPVMonth()));
                                ha.setPvbnow(cus.getRnPricePVUser1().add(cus.getPVMonth1()));
                                session.save(ha);
                            }
                        }
                    }
                    if (cus.getCircle() + cus.getCircleOld() >= 20) {
                        HistoryAwards ha;
                        if (cus.getAward10() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(10000));
                            ha.setPricePv(BigDecimal.valueOf(1000));
                            ha.setName("Thưởng lên cấp 20 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award10=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 50 && cus.getAward20() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(15000));
                            ha.setPricePv(BigDecimal.valueOf(1500));
                            ha.setName("Thưởng lên cấp 50 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award20=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 80 && cus.getAward30() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(15000));
                            ha.setPricePv(BigDecimal.valueOf(1500));
                            ha.setName("Thưởng lên cấp 80 cặp");
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award30=1,activeRank=1 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.executeUpdate();
                        }
                        if (cus.getCircle() + cus.getCircleOld() >= 112 && cus.getAward39() != 1) {
                            ha = new HistoryAwards();
                            ha.setCustomer(new Customer(cus.getID()));
                            ha.setPrice(BigDecimal.valueOf(30000));
                            ha.setPricePv(BigDecimal.valueOf(3000));
                            ha.setName("Thưởng lên cấp 112 cặp");
                            ha.setIsDeleted(Boolean.FALSE);
                            ha.setIsShow(Boolean.TRUE);
                            ha.setCustomerRankId(rankCustomerId);
                            ha.setCheckAwards(new CheckAwards(29));

                            session.save(ha);

                            q = session.createQuery("update RankNow set award39=1,activeRank=2,levelId=:level_id,level=2 where customer.id=:cus_id");
                            q.setParameter("cus_id", cus.getID());
                            q.setParameter("level_id", "Giamsat");
                            q.executeUpdate();
                        }
                    }
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

    public BigDecimal getNewestDeposit() {
        Session session = null;
        BigDecimal result = BigDecimal.ZERO;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createQuery("select pricePv from CustomerRankCustomer where isDeleted=0 order by dateCreated DESC");
                q.setMaxResults(1);
                result = (BigDecimal) q.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public BigDecimal getNewestAward() {
        Session session = null;
        BigDecimal result = BigDecimal.ZERO;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createQuery("select pricePv from HistoryAwards where isDeleted=0 order by dateCreated DESC");
                q.setMaxResults(1);
                result = (BigDecimal) q.uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return result;
    }

    public List getTop5DepositPVInMonth() {
        Calendar c = Calendar.getInstance();
        return getTop5DepositPV(c.get(Calendar.MONTH) + 1, c.get(Calendar.YEAR));
    }

    public List getTop5DepositPVInYear() {
        Calendar c = Calendar.getInstance();
        return getTop5DepositPV(null, c.get(Calendar.YEAR));
    }

    public List getTop5DepositPV(Integer month, Integer year) {
        Session session = null;
        List result = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Query q = session.createSQLQuery("GetTop5DepositPV :month,:year")
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

    @Override
    public void pageData(DefaultAdminPagination pagination) {
    }
}
