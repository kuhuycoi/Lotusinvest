package com.resources.facade;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfiguration {

    private static final HibernateConfiguration INSTANCE = new HibernateConfiguration();

    private static SessionFactory sessionFactory = HibernateConfiguration.INSTANCE.buildSessionFactory();

    private HibernateConfiguration() {
    }

    public static HibernateConfiguration getInstance() {
        return INSTANCE;
    }

    private SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration().configure();
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            ex.printStackTrace();
        }
        return sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

    public void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    public void flushSession(Session session) {
        if (session != null) {
            session.flush();
            session.clear();
        }
    }

    public void closeSession(Session session) {
        try {
            if (session != null && !session.isDirty()) {
                session.close();
            }
        } catch (Exception e) {

        }
    }
}
