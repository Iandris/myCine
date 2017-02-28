package com.mtyoung.persistence;

import com.mtyoung.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class MovieCastDaoTest {

    MovieCastDao dao;
    MovieCastLink link;
    ActorDao actorDao;
    Actor actor1;
    Actor actor2;
    Movie film1;
    Movie film2;
    MovieDao movieDao;
    Genre gen;
    GenreDao genDao;
    Format form;
    FormatDao formDao;
    Director dir;
    DirectorDao dirDao;
    Studio std;
    StudioDao stdDao;

    int newCast = 0;
    int newDir = 0;
    int newMovie1 = 0;
    int newMovie2 = 0;
    int newActor1 = 0;
    int newActor2 = 0;
    int newGen = 0;
    int newForm = 0;
    int newStudio = 0;

    @Before
    public void setup() {
        std = new Studio();
        stdDao = new StudioDao();
        std.setStudiotitle("Paramount");
        newStudio = stdDao.addStudio(std);

        form = new Format();
        formDao = new FormatDao();
        form.setFormattitle("DVD");
        newForm = formDao.addFormat(form);

        genDao = new GenreDao();
        gen = new Genre();
        gen.setGenretitle("Action");
        newGen = genDao.addGenre(gen);

        dirDao = new DirectorDao();
        dir = new Director();
        dir.setFname("Martin");
        dir.setLname("Scorsese");
        newDir = dirDao.addDirector(dir);

        movieDao = new MovieDao();
        film1 = new Movie();
        film1.setTitle("The Matrix");
        film1.setReleaseDate(LocalDate.of(1999,3,31));
        film1.setFormat(form);
        film1.setGenre(newGen);
        film1.setStudio(newStudio);
        film1.setDirector(newDir);
        film1.setImdbid("tt0133093");
        film1.setUpccode("883929454563");
        newMovie1 = movieDao.addMovie(film1);

        film2 = new Movie();
        film2.setTitle("Jaws");
        film2.setReleaseDate(LocalDate.of(2005,9,22));
        film2.setFormat(form);
        film2.setGenre(newGen);
        film2.setStudio(newStudio);
        film2.setDirector(newDir);
        film2.setImdbid("abcidkasb");
        film2.setUpccode("999999999999");
        newMovie2 = movieDao.addMovie(film2);

        actorDao = new ActorDao();
        actor1 = new Actor();
        actor2 = new Actor();

        actor1.setFname("Keanu");
        actor1.setLname("Reeves");
        newActor1 = actorDao.addActor(actor1);

        actor2.setFname("Marshal");
        actor2.setLname("Tucker");
        newActor2 = actorDao.addActor(actor2);

        dao = new MovieCastDao();
        link = new MovieCastLink();

        link.setFilmid(film1.getIdmovie());
        link.setActorid(actor1.getIdactor());
    }

    @After
    public void cleanup() {

        if (newCast != 0) {
            dao.deleteCast(newCast);
        }

        if (newMovie1 != 0) {
            movieDao.deleteMovie(newMovie1);
        }

        if (newMovie2 != 0) {
            movieDao.deleteMovie(newMovie2);
        }

        if (newActor1 != 0) {
            actorDao.deleteActor(newActor1);
        }

        if (newActor2 != 0) {
            actorDao.deleteActor(newActor2);
        }

        if (newDir != 0) {
            dirDao.deleteDirector(newDir);
        }

        if (newGen != 0) {
            genDao.deleteGenre(newGen);
        }

        if (newForm != 0) {
            formDao.deleteFormat(newForm);
        }

        if (newStudio != 0) {
            stdDao.deleteStudio(newStudio);
        }


    }

    @Test
    public void getAllCasts() throws Exception {
        newCast = dao.addCast(link);
        List<MovieCastLink> links = dao.getAllCasts();
        assertTrue("no cast returned", links.size() > 0);
    }

    @Test
    public void getCast() throws Exception {
        newCast = dao.addCast(link);
        assertEquals("cast id not returned", link.getIdmoviecast(), dao.getCast(newCast).getIdmoviecast());
        assertEquals("actor ID not returned", link.getActorid(), dao.getCast(newCast).getActorid());
        assertEquals("movie id not returned", link.getFilmid(), dao.getCast(newCast).getFilmid());
    }

    @Test
    public void addCast() throws Exception {
        newCast = dao.addCast(link);
        assertEquals("cast id not inserted", link.getIdmoviecast(), dao.getCast(newCast).getIdmoviecast());
        assertEquals("actor ID not inserted", link.getActorid(), dao.getCast(newCast).getActorid());
        assertEquals("movie id not inserted", link.getFilmid(), dao.getCast(newCast).getFilmid());
    }

    @Test
    public void deleteCast() throws Exception {
        dao.addCast(link);
        dao.deleteCast(link.getIdmoviecast());
        assertNull("cast link not deleted", dao.getCast(link.getIdmoviecast()));
    }

    @Test
    public void updateCast() throws Exception {
        newCast = dao.addCast(link);

        link.setActorid(actor2.getIdactor());
        link.setFilmid(film2.getIdmovie());

        dao.updateCast(link);

        assertEquals("cast id not updated", link.getIdmoviecast(), dao.getCast(newCast).getIdmoviecast());
        assertEquals("actor ID not updated", link.getActorid(), dao.getCast(newCast).getActorid());
        assertEquals("movie id not updated", link.getFilmid(), dao.getCast(newCast).getFilmid());
    }

}