package com.mtyoung.persistence;

import com.mtyoung.entity.Actor;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class ActorDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Actor> getAllActors() {
        List<Actor> actors = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            actors = session.createCriteria(Actor.class).list();
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return actors;
    }

    public Actor getActor(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Actor actor = null;
        try {
            actor = (Actor) session.get(Actor.class, id);
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
        return actor;
    }

    public int addActor(Actor actor) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(actor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteActor(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Actor actor = (Actor) session.get(Actor.class, id);
            session.delete(actor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }

    public void updateActor(Actor actor) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(actor);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }
}
