package com.mtyoung.persistence;

import com.mtyoung.entity.User;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/13/17.
 */
public class UserDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all users
     *
     * @return All users
     */
    public List<User> getAllUsers() {
        List<User> users = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            users = session.createCriteria(User.class).list();
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }
        return users;
    }


    /**
     * retrieve a user given their id
     *
     * @param id the user's id
     * @return user
     */
    public User getUser(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        User user = null;
        try {
            user = (User) session.get(User.class, id);
            return user;
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
        return user;
    }

    /**
     * add a user
     *
     * @param user
     * @return the id of the inserted record
     */
    public int addUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        }finally {
            session.close();
            return id;
        }
    }


    /**
     * delete a user by id
     * @param id the user's id
     */
    public void deleteUser(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }

    /**
     * Update the user
     * @param user
     */

    public void updateUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }
}
