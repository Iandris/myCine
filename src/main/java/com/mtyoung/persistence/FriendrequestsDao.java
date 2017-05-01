package com.mtyoung.persistence;

import com.mtyoung.entity.Friendrequests;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 3/6/17.
 */
public class FriendrequestsDao {
    private final Logger logger = Logger.getLogger(this.getClass());
    public List<Friendrequests> getAllFriendRequests() {
        List<Friendrequests> friendrequestss = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            friendrequestss = session.createCriteria(Friendrequests.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        return friendrequestss;
    }

    public Friendrequests getFriendRequest(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Friendrequests friendrequest = null;
        try {
            friendrequest = (Friendrequests) session.get(Friendrequests.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return friendrequest;
    }

    public int addFriendRequest(Friendrequests friendrequests) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(friendrequests);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }
        return id;
    }

    public void deleteFriendRequest(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Friendrequests friendrequest = (Friendrequests) session.get(Friendrequests.class, id);
            session.delete(friendrequest);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateFriendRequest(Friendrequests friendrequest) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(friendrequest);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public Friendrequests findFriendRequestByHashId(String reqId) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Friendrequests friendrequests = null;
        try {
            friendrequests = (Friendrequests) session.createQuery("from com.mtyoung.entity.Friendrequests U where U.reqid = :reqid")
                    .setString("reqid", reqId)
                    .uniqueResult();
            return friendrequests;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return friendrequests;
    }
}
