package com.mtyoung.persistence;
import com.mtyoung.entity.Movie;

import com.sun.xml.internal.bind.v2.TODO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/13/17.
 */
public class MovieDaoTest {

    Movie film;
    MovieDao dao;
    int newMovie;

    @Before
    public void setup() {
        film = new Movie();
        dao = new MovieDao();

        film.setTitle("The Matrix");
        film.setReleaseDate(LocalDate.of(1999,3,31));
        film.setFormat(1);
        film.setGenre(2);
        film.setStudio(1);
        film.setDirector(2);
        film.setImdbid("tt0133093");
        film.setUpccode("883929454563");
    }

    @After
    public void cleanup() {
        if (newMovie != 0) {
            dao.deleteMovie(newMovie);
        }
    }


    @Test
    public void getAllMovies() throws Exception {
        newMovie = dao.addMovie(film);
        List<Movie> movies = dao.getAllMovies();
        assertTrue(movies.size() > 0);
    }

    @Test
    public void getMovie() throws Exception {
        newMovie = dao.addMovie(film);
        assertNotNull("no move returned", dao.getMovie(newMovie));
        assertEquals("title not returned correctly", film.getTitle(), dao.getMovie(newMovie).getTitle().toString());
        assertEquals("release date not returned correctly", film.getReleaseDate(), dao.getMovie(newMovie).getReleaseDate());
        assertEquals("imdb id not returned correctly", film.getImdbid(), dao.getMovie(newMovie).getImdbid());
        assertEquals("upc code not returned", film.getUpccode(), dao.getMovie(newMovie).getUpccode());
        assertEquals("Director not returned", film.getDirector(), dao.getMovie(newMovie).getDirector());
        assertEquals("Format not returned", film.getFormat(), dao.getMovie(newMovie).getFormat());
        assertEquals("Genre not returned", film.getGenre(), dao.getMovie(newMovie).getGenre());
        assertEquals("Studio not returned", film.getStudio(), dao.getMovie(newMovie).getStudio());
        assertEquals("Movie ID not returned", film.getIdmovie(), dao.getMovie(newMovie).getIdmovie());

    }

    @Test
    public void addMovie() throws Exception {
        newMovie = dao.addMovie(film);
        assertNotEquals("no movie added", 0, dao.getMovie(newMovie));
    }

    @Test
    public void deleteMovie() throws Exception {
        int newMovie = dao.addMovie(film);

        if (newMovie != 0) {
            dao.deleteMovie(newMovie);
        }

        assertNull("Movie not successfully deleted", dao.getMovie(newMovie));
    }

    @Test
    public void updateMovie() throws Exception {
        newMovie = dao.addMovie(film);
        film.setTitle("Jaws");
        film.setReleaseDate(LocalDate.of(2005,9,22));
        film.setFormat(2);
        film.setGenre(1);
        film.setStudio(2);
        film.setDirector(1);
        film.setImdbid("abcidkasb");
        film.setUpccode("999999999999");

        dao.updateMovie(film);
        assertEquals("title not updated correctly", film.getTitle(), dao.getMovie(newMovie).getTitle().toString());
        assertEquals("release date not updated correctly", film.getReleaseDate(), dao.getMovie(newMovie).getReleaseDate());
        assertEquals("imdb id not updated correctly", film.getImdbid(), dao.getMovie(newMovie).getImdbid());
        assertEquals("upc code not updated", film.getUpccode(), dao.getMovie(newMovie).getUpccode());
        assertEquals("Director not updated", film.getDirector(), dao.getMovie(newMovie).getDirector());
        assertEquals("Format not updated", film.getFormat(), dao.getMovie(newMovie).getFormat());
        assertEquals("Genre not updated", film.getGenre(), dao.getMovie(newMovie).getGenre());
        assertEquals("Studio not updated", film.getStudio(), dao.getMovie(newMovie).getStudio());
        assertEquals("Movie ID not updated", film.getIdmovie(), dao.getMovie(newMovie).getIdmovie());

    }

}