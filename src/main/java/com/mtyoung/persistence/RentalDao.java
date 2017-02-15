package com.mtyoung.persistence;

import com.mtyoung.entity.Rental;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class RentalDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Rental> getAllRentals() {
        List<Rental> rentals = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            rentals = session.createCriteria(Rental.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return rentals;
    }

    public Rental getRental(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Rental rental = null;
        try {
            rental = (Rental) session.get(Rental.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return rental;
    }

    public int addRental(Rental rental) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(rental);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteRental(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Rental rental = (Rental) session.get(Rental.class, id);
            session.delete(rental);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateRental(Rental rental) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(rental);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
