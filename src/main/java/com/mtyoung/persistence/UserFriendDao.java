package com.mtyoung.persistence;

import com.mtyoung.entity.UserFriends;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class UserFriendDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all users
     *
     * @return All users
     */
    public List<UserFriends> getAllFriends() {
        List<UserFriends> friends = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            friends = session.createCriteria(UserFriends.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }
        return friends;
    }


    /**
     * retrieve a user given their id
     *
     * @param id the user's id
     * @return user
     */
    public UserFriends getFriend(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        UserFriends friend = null;
        try {
            friend = (UserFriends) session.get(UserFriends.class, id);
            return friend;
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return friend;
    }

    /**
     * add a user
     *
     * @param friend
     * @return the id of the inserted record
     */
    public int addFriend(UserFriends friend) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int) session.save(friend);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
            return id;
        }
    }

    /**
     * delete a user by id
     * @param id the user's id
     */
    public void deleteFriend(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UserFriends friend = (UserFriends) session.get(UserFriends.class, id);
            session.delete(friend);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    /**
     * Update the user
     * @param friend
     */

    public void updateFriend(UserFriends friend) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(friend);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
