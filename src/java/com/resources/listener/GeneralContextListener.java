package com.resources.listener;

import com.resources.facade.HibernateConfiguration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
public class GeneralContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        HibernateConfiguration.getInstance().shutdown();
    }
}
