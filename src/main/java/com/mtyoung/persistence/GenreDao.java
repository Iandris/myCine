package com.mtyoung.persistence;

import com.mtyoung.entity.Genre;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/14/17.
 */
public class GenreDao {
    private final Logger logger = Logger.getLogger(this.getClass());
    public List<Genre> getAllGenres() {
        List<Genre> genres = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            genres = session.createCriteria(Genre.class).list();
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return genres;
    }

    public Genre getGenre(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Genre genre = null;
        try {
            genre = (Genre) session.get(Genre.class, id);
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return genre;
    }

    public int addGenre(Genre genre) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(genre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteGenre(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Genre genre = (Genre) session.get(Genre.class, id);
            session.delete(genre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public void updateGenre(Genre genre) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(genre);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
    }

    public Genre getGenreByTitle(String title) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Genre genre = null;
        try {
            genre = (Genre) session.createQuery("from com.mtyoung.entity.Genre U where U.genretitle = :genreTitle")
                    .setString("genreTitle", title)
                    .uniqueResult();
            return genre;
        } catch (HibernateException e) {
            logger.error(e.getMessage());
        } finally {
            session.close();
        }
        return genre;
    }
}
