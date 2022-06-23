package repositories;

import entity.Route;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.util.List;


public class RouteDAO {
    static Logger logger = LogManager.getLogger(RouteDAO.class);

    public static List<Route> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Route> routes = session.createQuery("from Route").getResultList();
            return routes;
        } catch (Throwable e) {
            System.out.println(e.getMessage());
            logger.error(e);
        }
        return null;
    }

    public static boolean add(Route route) {
        try(  Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(route);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Route findById(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query query = session.createQuery(" from Route where id=:route_id");
            query.setParameter("route_id", id);
            return (Route) query.getSingleResult();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean removeStudent(int id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Route route = session.load(Route.class, id);
            session.delete(route);
            session.beginTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
