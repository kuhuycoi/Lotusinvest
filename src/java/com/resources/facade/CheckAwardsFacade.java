package com.resources.facade;

import com.resources.entity.CheckAwards;
import com.resources.entity.Module;
import com.resources.entity.RankNow;
import com.resources.pagination.admin.DefaultAdminPagination;
import com.resources.pagination.admin.HistoryPagination;
import com.resources.utils.StringUtils;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class CheckAwardsFacade extends AbstractFacade{

    public CheckAwardsFacade() {
        super(RankNow.class);
    }

    public void pageData(HistoryPagination historyPagination) {
        Session session=null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                Criteria cr = session.createCriteria(CheckAwards.class);
                cr.add(Restrictions.and(Restrictions.eq("isDeleted", false), Restrictions.eq("isShow", true)));                
                
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
                        .add(Projections.property("id"),"id")
                        .add(Projections.property("name"),"name")
                        .add(Projections.property("price"),"price")
                        .add(Projections.property("pricePv"),"pricePv")
                        .add(Projections.property("percent"),"percent")
                        .add(Projections.property("time"),"time"))
                        .setResultTransformer(Transformers.aliasToBean(CheckAwards.class));                
                cr.setFirstResult(historyPagination.getFirstResult());
                cr.setMaxResults(historyPagination.getDisplayPerPage());
                cr.addOrder(historyPagination.isAsc() ? Order.asc(historyPagination.getOrderColmn()) : Order.desc(historyPagination.getOrderColmn()));
                historyPagination.setDisplayList(cr.list());
            }
        } catch (Exception e) {
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            HibernateConfiguration.getInstance().closeSession(session);
        }
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
