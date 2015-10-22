package com.resources.facade;

import com.resources.entity.CustomerType;
import com.resources.entity.Module;
import com.resources.pagination.admin.DefaultAdminPagination;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

public class CustomerTypeFacade extends AbstractFacade implements Serializable {

    public CustomerTypeFacade() {
        super(CustomerType.class);
    }

    public List<CustomerType> findAllAvailableCustomerType() {
        Transaction trans = null;
        List<CustomerType> list = null;
        Session session = null;
        try {
            session = HibernateConfiguration.getInstance().openSession();
            if (session != null) {
                trans = session.beginTransaction();
                Criteria cr = session.createCriteria(CustomerType.class);
                cr.setProjection(Projections.projectionList()
                        .add(Projections.property("id"), "id")
                        .add(Projections.property("name"), "name"))
                        .setResultTransformer(Transformers.aliasToBean(CustomerType.class));
                cr.add(Restrictions.eq("isDelete", false));
                list = cr.list();
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
        } finally {
            HibernateConfiguration.getInstance().closeSession(session);
        }
        return list;
    }

    @Override
    public void pageData(DefaultAdminPagination pagination) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
