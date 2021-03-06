package com.mtyoung.persistence;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class RentalDao {
        private final Logger logger = Logger.getLogger(this.getClass());

    public List<Rental> getAllRentals() {
        List<Rental> rentals = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            rentals = session.createCriteria(Rental.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<Rental> getRentalsByRenter(User renterID) {
        List<Rental> rentals = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            rentals = session.createCriteria(Rental.class)
                    .add(Restrictions.eq("renterid", renterID)
                    ).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return rentals;
    }

    public Rental getRentalByMovieID(UserMovieLink link) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Rental rental = null;
        try {
            rental = (Rental) session.createCriteria(Rental.class)
                    .add(Restrictions.eq("movieid", link)
                    ).uniqueResult();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return rental;
    }
}
