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
        private final Logger logger = Logger.getLogger(this.getClass());

    public List<Wishlist> getAllWishListItems() {
        List<Wishlist> listItems = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            listItems = session.createCriteria(Wishlist.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<Wishlist> getWishListByUserID(int userid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        List<Wishlist> links = null;
        try {

            links = session.createQuery("from com.mtyoung.entity.Wishlist U where U.userid = :uuid order by U.movieid.title")
                    .setString("uuid", String.valueOf(userid))
                    .list();
            return links;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return links;
    }

    public Wishlist getLinkByUserMovie(int userid, int movieid) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Wishlist link = null;
        try {

            link = (Wishlist) session.createQuery("from com.mtyoung.entity.Wishlist U where U.userid = :uuid and U.movieid = :movieid")
                    .setString("uuid", String.valueOf(userid))
                    .setString("movieid", String.valueOf(movieid))
                    .uniqueResult();
            return link;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return link;
    }
}
