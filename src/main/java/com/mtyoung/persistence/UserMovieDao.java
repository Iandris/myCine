package com.mtyoung.persistence;

import com.mtyoung.entity.UserMovieLink;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class UserMovieDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<UserMovieLink> getAllMovieLinks() {
        List<UserMovieLink> links = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            links = session.createCriteria(UserMovieLink.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return links;
    }

    public UserMovieLink getMovieLink(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        UserMovieLink link = null;
        try {
            link = (UserMovieLink) session.get(UserMovieLink.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return link;
    }

    public int addUserMovie(UserMovieLink link) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteMovieLink(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            UserMovieLink link = (UserMovieLink) session.get(UserMovieLink.class, id);
            session.delete(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateMovieLink(UserMovieLink link) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public List<UserMovieLink> getMoviesLinkByUserID(int userid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserMovieLink> links = null;
        try {

            links = session.createQuery("from com.mtyoung.entity.UserMovieLink U where U.userid = :uuid")
                    .setString("uuid", String.valueOf(userid))
                    .list();
            return links;
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return links;
    }

    public UserMovieLink getLinkByUserMovie(int userid, int movieid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        UserMovieLink link = null;
        try {

            link = (UserMovieLink) session.createQuery("from com.mtyoung.entity.UserMovieLink U where U.userid = :uuid and U.movieid = :movieid")
                    .setString("uuid", String.valueOf(userid))
                    .setString("movieid", String.valueOf(movieid))
                    .uniqueResult();
            return link;
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return link;
    }
}
