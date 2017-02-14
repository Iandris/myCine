package com.mtyoung.persistence;

import com.mtyoung.entity.Movie;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/13/17.
 */
public class MovieDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Movie> getAllMovies() {
        List<Movie> movies = null;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        try {
            movies = session.createCriteria(Movie.class).list();
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return movies;
    }

    public Movie getMovie(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Movie movie = null;
        try {
            movie = (Movie) session.get(Movie.class, id);
        } catch (HibernateException e) {
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
        return movie;
    }

    public int addMovie(Movie movie) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        int id = 0;
        try {
            tx = session.beginTransaction();
            id = (int)session.save(movie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        }finally {
            session.close();
        }

        return id;
    }

    public void deleteMovie(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Movie movie = (Movie) session.get(Movie.class, id);
            session.delete(movie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }

    public void updateMovie(Movie movie) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(movie);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            log.info(e.getMessage().toString());
        } finally {
            session.close();
        }
    }
}
