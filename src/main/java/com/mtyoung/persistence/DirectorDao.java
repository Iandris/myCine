package com.mtyoung.persistence;

import com.mtyoung.entity.Director;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class DirectorDao {
    private final Logger logger = Logger.getLogger(this.getClass());
    public List<Director> getAllDirectors() {
        List<Director> directors = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            directors = session.createCriteria(Director.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());

        }finally {
            session.close();
        }

        return directors;
    }

    public Director getDirector(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Director director = null;
        try {
            director = (Director) session.get(Director.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());

        } finally {
            session.close();
        }
        return director;
    }

    public int addDirector(Director director) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(director);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());

        }finally {
            session.close();
        }

        return id;
    }

    public void deleteDirector(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Director director = (Director) session.get(Director.class, id);
            session.delete(director);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());

        } finally {
            session.close();
        }
    }

    public void updateDirector(Director director) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(director);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());

        } finally {
            session.close();
        }
    }

    public Director getDirectorByLastFirst (String lname, String fname) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Director dir = null;
        try {
            dir = (Director) session.createQuery("from com.mtyoung.entity.Director U where U.lname = :last and U.fname = :first")
                    .setString("first", fname)
                    .setString("last", lname)
                    .uniqueResult();
            return dir;
        } catch (HibernateException e) {
            logger.error(e.getMessage());

        } finally {
            session.close();
        }
        return dir;
    }
}
