package com.mtyoung.persistence;

import com.mtyoung.entity.Studio;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class StudioDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Studio> getAllStudios() {
        List<Studio> studios = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            studios = session.createCriteria(Studio.class).list();
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return studios;
    }

    public Studio getStudio(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Studio studio = null;
        try {
            studio = (Studio) session.get(Studio.class, id);
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
        return studio;
    }

    public int addStudio(Studio studio) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(studio);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteStudio(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Studio studio = (Studio) session.get(Studio.class, id);
            session.delete(studio);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }

    public void updateStudio(Studio studio) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(studio);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }
}
