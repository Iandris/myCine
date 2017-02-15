package com.mtyoung.persistence;

import com.mtyoung.entity.MovieCastLink;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class MovieCastDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<MovieCastLink> getAllCasts() {
        List<MovieCastLink> casts = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            casts = session.createCriteria(MovieCastLink.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return casts;
    }

    public MovieCastLink getCast(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        MovieCastLink cast = null;
        try {
            cast = (MovieCastLink) session.get(MovieCastLink.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return cast;
    }

    public int addCast(MovieCastLink cast) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(cast);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteCast(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            MovieCastLink cast = (MovieCastLink) session.get(MovieCastLink.class, id);
            session.delete(cast);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateCast(MovieCastLink cast) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(cast);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
