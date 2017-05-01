package com.mtyoung.persistence;

import com.mtyoung.entity.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class UserRoleDao {
        private final Logger logger = Logger.getLogger(this.getClass());

    public List<UserRole> getAllRoles() {
        List<UserRole> roles = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            roles = session.createCriteria(UserRole.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return roles;
    }

    public UserRole getRole(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        UserRole role = null;
        try {
            role = (UserRole) session.get(UserRole.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return role;
    }

    public int addRole(UserRole role) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteRole(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            UserRole role = (UserRole) session.get(UserRole.class, id);
            session.delete(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateRole(UserRole role) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(role);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public UserRole getRoleByUserName(String user) {

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        UserRole role = null;
        try {
             role = (UserRole) session.createQuery("from com.mtyoung.entity.UserRole U where U.user_name = :username")
                    .setString("username", user)
                    .uniqueResult();
            return role;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return role;
    }
}
