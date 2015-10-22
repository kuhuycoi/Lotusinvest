package com.resources.facade;

import com.resources.entity.Customer;
import com.resources.entity.Module;
import com.resources.entity.RankNow;
import com.resources.pagination.admin.DefaultAdminPagination;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class RankNowFacade extends AbstractFacade{

    public RankNowFacade() {
        super(RankNow.class);
    }

    public RankNow findRankNowByCusID(Customer customer) {
        Transaction trans = null;
        RankNow rankNow = null;
        Session session=null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                trans = session.beginTransaction();
                Criteria cr = session.createCriteria(RankNow.class);
                cr.add(Restrictions.eq("customer", customer));
                rankNow = (RankNow) cr.uniqueResult();
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
            Logger.getLogger(Module.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return rankNow;
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
