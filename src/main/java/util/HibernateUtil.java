package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {

        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println(ex.getMessage());
        }
        return sessionFactory;
    }
}

