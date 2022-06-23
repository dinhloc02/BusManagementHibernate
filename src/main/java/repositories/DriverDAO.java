package repositories;

import entity.Driver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class DriverDAO {
    static Logger logger = LogManager.getLogger(DriverDAO.class);

    public static List<Driver> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Driver> drivers = session.createQuery("from Driver ").getResultList();
            return drivers;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            logger.error(e);
        }
        return null;
    }

    public static boolean add(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(driver);
            session.beginTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            logger.error(e);
        }
        return false;
    }

    public boolean removeStudent(Driver driver) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(driver);
            session.beginTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Driver findByid(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Driver where id = :driver_id ");
            query.setParameter("driver_id", id);
            session.beginTransaction().commit();
            return (Driver) query.getSingleResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
