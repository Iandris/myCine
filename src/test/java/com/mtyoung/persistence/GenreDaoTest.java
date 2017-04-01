package com.mtyoung.persistence;

import com.mtyoung.entity.Genre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class GenreDaoTest {

    Genre gen;
    GenreDao dao;

    int newGen = 0;

    @Before
    public void startup() {
        gen = new Genre();
        dao = new GenreDao();
        gen.setGenretitle("Action");
    }

    @After
    public void cleanup() {
        if (newGen != 0) {
            dao.deleteGenre(newGen);
        }
    }

    @Test
    public void getAllGenres() throws Exception {
        newGen = dao.addGenre(gen);
        List<Genre> genres = dao.getAllGenres();
        assertTrue(genres.size() > 0);
    }

    @Test
    public void getGenre() throws Exception {
        newGen = dao.addGenre(gen);
        assertEquals("genre description not returned", gen.getGenretitle(), dao.getGenre(newGen).getGenretitle());
        assertEquals("genre ID not returned", gen.getIdgenre(), dao.getGenre(newGen).getIdgenre());
    }

    @Test
    public void addGenre() throws Exception {
        newGen = dao.addGenre(gen);
        assertEquals("genre description not inserted", gen.getGenretitle(), dao.getGenre(newGen).getGenretitle());
        assertEquals("genre ID not inserted", gen.getIdgenre(), dao.getGenre(newGen).getIdgenre());
    }

    @Test
    public void deleteGenre() throws Exception {
        dao.addGenre(gen);
        dao.deleteGenre(gen.getIdgenre());
        assertNull("movie not deleted", dao.getGenre(gen.getIdgenre()));
    }

    @Test
    public void updateGenre() throws Exception {
        newGen = dao.addGenre(gen);

        gen.setGenretitle("Adventure");

        dao.updateGenre(gen);

        assertEquals("desc not updated", gen.getGenretitle(), dao.getGenre(newGen).getGenretitle());
    }

    @Test
    public void getGenreByTitle() throws Exception {
        newGen = dao.addGenre(gen);
        assertEquals("no genres returned", gen.getIdgenre(), dao.getGenreByTitle(gen.getGenretitle()).getIdgenre());
    }
}