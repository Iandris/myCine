package com.mtyoung.persistence;

import com.mtyoung.entity.UserFriends;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class UserFriendDao {
        private final Logger logger = Logger.getLogger(this.getClass());

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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<UserFriends> getFriendsByUser(int userid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<UserFriends> links = null;
        try {

            links = session.createCriteria(UserFriends.class)
                    .add(Restrictions.disjunction()
                    .add(Restrictions.eq("frienda", userid))
                    .add(Restrictions.eq("friendb", userid))
                    ).list();

            return links;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return links;
    }
}
