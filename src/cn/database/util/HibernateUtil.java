/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.util;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author MK
 */
public class HibernateUtil {

    private static final Configuration configuration;
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            configuration = new Configuration().configure(new File("hibernate.cfg.xml"));
    
            sessionFactory = configuration.buildSessionFactory();

        } catch (Throwable ex) {
            // Log the exception.
           // ex.printStackTrace();
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }
    static Session session;

    public static Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        } else {
            if (!session.isOpen()) {
                session.close();
                session = sessionFactory.openSession();
            }
        }

        return session;
    }
}
