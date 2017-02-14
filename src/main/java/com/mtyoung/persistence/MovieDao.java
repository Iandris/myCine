package com.mtyoung.persistence;

import com.mtyoung.entity.Movie;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Mike on 2/13/17.
 */
public class MovieDao {
    private final Logger log = Logger.getLogger(this.getClass());

    public List<Movie> getAllMovies() {
        List<Movie> movies;
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        movies = session.createCriteria(Movie.class).list();
        session.close();
        return movies;
    }

    public Movie getMovie(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Movie movie = (Movie) session.get(Movie.class, id);
        return movie;
    }

    public int addMovie(Movie movie) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int)session.save(movie);
        transaction.commit();
        return id;
    }

    public void deleteMovie(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Movie movie = getMovie(id);
        session.delete(movie);
        transaction.commit();
    }

    public void updateMovie(Movie movie) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(movie);
        transaction.commit();
    }
}
