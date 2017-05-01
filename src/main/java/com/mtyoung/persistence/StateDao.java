package com.mtyoung.persistence;

import com.mtyoung.entity.State;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class StateDao {
        private final Logger logger = Logger.getLogger(this.getClass());

    public List<State> getAllStates() {
        List<State> states = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            states = session.createCriteria(State.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return states;
    }

    public State getState(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        State state = null;
        try {
            state = (State) session.get(State.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return state;
    }

    public int addState(State state) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(state);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteState(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            State state = (State) session.get(State.class, id);
            session.delete(state);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateState(State state) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(state);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }
}
