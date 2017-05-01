package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * AddressDao class for mycine project, responsible for db interaction Full CRUD related to the Address Entity
 */
public class AddressDao {
    private final Logger logger = Logger.getLogger(this.getClass());
    /**
     * getAllAddresses method, returns a list of all Address entities found in local db
     * @return
     */
    public List<Address> getAllAddresses() {
        List<Address> mails = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            mails = session.createCriteria(Address.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return mails;
    }

    /**
     * getAddress method, returns a single matched Address entity found in local db, requires 1 integer parameter for
     * id on Address entity
     * @param id
     * @return
     */
    public Address getAddress(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Address mail = null;
        try {
            mail = (Address) session.get(Address.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return mail;
    }

    /**
     * addAddress method, adds a single Address entity to local db, requires 1 Address parameter returns int value of ID
     * on entity in db
     * @param mail
     * @return
     */
    public int addAddress(Address mail) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return id;
    }

    /**
     * deleteAddress method, locates a single matching Address entity and deletes from local db, requires 1 int parameter
     * which is the ID on the Address entity to be located
     * @param id
     */
    public void deleteAddress(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Address mail = (Address) session.get(Address.class, id);
            session.delete(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * updateAddress method, locates a single Address entity and updates its attributes and saves back to db
     * requires 1 Address object parameter
     * @param mail
     */
    public void updateAddress(Address mail) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }
}
