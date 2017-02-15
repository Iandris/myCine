package com.mtyoung.persistence;

import com.mtyoung.entity.Wishlist;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class WishlistDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Wishlist> getAllWishListItems() {
        List<Wishlist> listItems = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            listItems = session.createCriteria(Wishlist.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return listItems;
    }

    public Wishlist getWishListItem (int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Wishlist listItem = null;
        try {
            listItem = (Wishlist) session.get(Wishlist.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return listItem;
    }

    public int addWishListItem(Wishlist listItem) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(listItem);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteWishListItem(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Wishlist listItem = (Wishlist) session.get(Wishlist.class, id);
            session.delete(listItem);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateWishListItem(Wishlist listItem) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(listItem);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
