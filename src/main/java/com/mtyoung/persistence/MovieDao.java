package com.mtyoung.persistence;

import com.mtyoung.entity.Movie;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.entity.Wishlist;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
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
            log.error("Hibernate Exception", e);
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
            log.error("Hibernate Exception", e);
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
            log.error("Hibernate Exception", e);
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
            log.error("Hibernate Exception", e);
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
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
    }

    public List<Movie> getRecentMovies(LocalDate localDate) {
        Date old = Date.from(localDate.minusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date newest = Date.from(localDate.plusDays(14).atStartOfDay(ZoneId.systemDefault()).toInstant());

        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        List<Movie> recents = null;
        try {

            recents = session.createQuery("from com.mtyoung.entity.Movie U where U.releaseDate > :old and U.releaseDate < :newer")
                    .setDate("old", old)
                    .setDate("newer", newest)
                    .list();
            return recents;
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return recents;
    }

    public Movie getMovieByIMDB(String imdbID) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = null;
        Movie movie = null;
        try {
            movie = (Movie) session.createQuery("from com.mtyoung.entity.Movie U where U.imdbid = :imdb")
                    .setString("imdb", imdbID)
                    .uniqueResult();
            return movie;
        } catch (HibernateException e) {
            log.error("Hibernate Exception", e);
        } finally {
            session.close();
        }
        return movie;
    }

    public List<Movie> getMovieListByLinks(List<UserMovieLink> movieIDs) {
        List<Movie> movies = null;

        for (UserMovieLink link : movieIDs
             ) {
            movies.add(getMovie(link.getMovieid()));
        }

        return movies;
    }

    public List<Movie> getMovieListByWishlist(List<Wishlist> movieIDs) {
        List<Movie> movies = null;

        for (Wishlist link : movieIDs
                ) {
            movies.add(getMovie(link.getMovieid()));
        }

        return movies;
    }
}
