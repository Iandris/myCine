package com.mtyoung.persistence;

import com.mtyoung.entity.FriendLink;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class FriendLinkDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<FriendLink> getAllFriendLinks() {
        List<FriendLink> links = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            links = session.createCriteria(FriendLink.class).list();
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return links;
    }

    public FriendLink getFriendLink(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        FriendLink link = null;
        try {
            link = (FriendLink) session.get(FriendLink.class, id);
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
        return link;
    }

    //TODO fix add link not returning a value, not inserting row

    public int addFriendLink(FriendLink link) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int) session.save(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        }finally {
            session.close();
            return id;
        }
    }

    public void deleteFriendLink(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            FriendLink link = (FriendLink) session.get(FriendLink.class, id);
            session.delete(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }

    public void updateFriendLink(FriendLink link) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(link);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }
}
