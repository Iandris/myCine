package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class AddressDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Address> getAllAddresses() {
        List<Address> mails = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            mails = session.createCriteria(Address.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return mails;
    }

    public Address getAddress(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Address mail = null;
        try {
            mail = (Address) session.get(Address.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return mail;
    }

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
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

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
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateAddress(Address mail) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(mail);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
