package com.mtyoung.persistence;

import com.mtyoung.entity.Message;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class MessageDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Message> getAllMessages() {
        List<Message> messages = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            messages = session.createCriteria(Message.class).list();
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return messages;
    }

    public Message getMessage(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Message message = null;
        try {
            message = (Message) session.get(Message.class, id);
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return message;
    }

    public int addMessage(Message message) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteMessage(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Message message = (Message) session.get(Message.class, id);
            session.delete(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public void updateMessage(Message message) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(message);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }
}
