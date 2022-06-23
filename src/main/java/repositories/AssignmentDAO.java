package repositories;

import entity.Assignment;
import entity.Route;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

public class AssignmentDAO {
    public static List<Assignment> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Assignment> assignments = session.createQuery("from Assignment ").getResultList();
            return assignments;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static List<Assignment> getAllSort() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Assignment> assignments = session.createQuery("from Assignment a order by a.driverId ").getResultList();
            return assignments;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean add(Assignment assignment) {
        try(  Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(assignment);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
