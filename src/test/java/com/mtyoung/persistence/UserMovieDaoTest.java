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
public class UserMovieDaoTest {

    Address mail;
    AddressDao mailDao;
    User bob1;
    User bob2;
    UserDao userDao;
    Genre gen;
    GenreDao genDao;
    Format form;
    FormatDao formDao;
    Director dir;
    DirectorDao dirDao;
    Studio std;
    StudioDao stdDao;
    Movie film1;
    Movie film2;
    MovieDao movieDao;
    UserMovieLink link;
    UserMovieDao dao;

    int newDir = 0;
    int newGen = 0;
    int newForm = 0;
    int newStudio = 0;
    int newMovie1 = 0;
    int newMovie2 = 0;
    int newMail = 0;
    int newUser1 = 0;
    int newUser2 = 0;
    int newMovieLink = 0;

    @Before
    public void setUp() throws Exception {
        //prep address table first, or user insert will fail on constraint
        mailDao = new AddressDao();
        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("nnnnn");
        mail.setCity("Watertown");
        mail.setState(49);
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);


        userDao = new UserDao();
        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setAddressid(newMail);
        bob1.setUser_name("bob5@email.com");
        bob1.setCellnumber("6666666666");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        bob1.setPassword("Password");
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("Michael");
        bob2.setLname("Smith");
        bob2.setAddressid(newMail);
        bob2.setUser_name("bob6@email.com");
        bob2.setCellnumber("5555555555");
        bob2.setReminderthreshold(2);
        bob2.setDefaultrentalperiod(5);
        bob2.setPassword("Password");
        newUser2 = userDao.addUser(bob2);

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
        film1.setFormat(newForm);
        film1.setGenre(newGen);
        film1.setStudio(newStudio);
        film1.setDirector(newDir);
        film1.setImdbid("tt0133093");
        film1.setUpccode("883929454563");
        newMovie1 = movieDao.addMovie(film1);

        film2 = new Movie();
        film2.setTitle("Jaws");
        film2.setReleaseDate(LocalDate.of(2005,9,22));
        film2.setFormat(newForm);
        film2.setGenre(newGen);
        film2.setStudio(newStudio);
        film2.setDirector(newDir);
        film2.setImdbid("abcidkasb");
        film2.setUpccode("999999999999");
        newMovie2 = movieDao.addMovie(film2);

        dao = new UserMovieDao();
        link = new UserMovieLink();
        link.setUserid(bob1.getUuid());
        link.setQuantity(1);
        link.setMovieid(film1.getIdmovie());
        link.setStarrating(5);

    }

    @After
    public void tearDown() throws Exception {
        if (newMovieLink != 0) {
            dao.deleteMovieLink(newMovieLink);
        }

        if (newUser2 != 0) {
            userDao.deleteUser(newUser2);
        }

        if (newUser1 != 0) {
            userDao.deleteUser(newUser1);
        }

        if (newMail != 0) {
            mailDao.deleteAddress(newMail);
        }

        if (newMovie2 != 0) {
            movieDao.deleteMovie(newMovie2);
        }

        if (newMovie1 != 0) {
            movieDao.deleteMovie(newMovie1);
        }

        if (newStudio != 0) {
            stdDao.deleteStudio(newStudio);
        }

        if (newForm != 0) {
            formDao.deleteFormat(newForm);
        }

        if (newGen != 0) {
            genDao.deleteGenre(newGen);
        }

        if (newDir != 0) {
            dirDao.deleteDirector(newDir);
        }
    }

    @Test
    public void getAllMovieLinks() throws Exception {
        newMovieLink = dao.addUserMovie(link);
        List<UserMovieLink> links = dao.getAllMovieLinks();
        assertTrue("no user movie links returned", links.size() > 0);
    }

    @Test
    public void getMovieLink() throws Exception {
        newMovieLink = dao.addUserMovie(link);
        assertEquals("link id not returned", link.getLinkid(), dao.getMovieLink(newMovieLink).getLinkid());
        assertEquals("link movie not returned", link.getMovieid(), dao.getMovieLink(newMovieLink).getMovieid());
        assertEquals("link quantity not returned", link.getQuantity(), dao.getMovieLink(newMovieLink).getQuantity());
        assertEquals("link owner not returned", link.getUserid(), dao.getMovieLink(newMovieLink).getUserid());
        assertEquals("link rating not returned", link.getStarrating(), dao.getMovieLink(newMovieLink).getStarrating());

    }

    @Test
    public void addUserMovie() throws Exception {
        newMovieLink = dao.addUserMovie(link);
        assertEquals("link id not inserted", link.getLinkid(), dao.getMovieLink(newMovieLink).getLinkid());
        assertEquals("link movie not inserted", link.getMovieid(), dao.getMovieLink(newMovieLink).getMovieid());
        assertEquals("link quantity not inserted", link.getQuantity(), dao.getMovieLink(newMovieLink).getQuantity());
        assertEquals("link owner not inserted", link.getUserid(), dao.getMovieLink(newMovieLink).getUserid());
        assertEquals("link rating not inserted", link.getStarrating(), dao.getMovieLink(newMovieLink).getStarrating());
    }

    @Test
    public void deleteMovieLink() throws Exception {
        dao.addUserMovie(link);
        dao.deleteMovieLink(link.getLinkid());
        assertNull("link not removed", dao.getMovieLink(link.getLinkid()));
    }

    @Test
    public void updateMovieLink() throws Exception {
        newMovieLink = dao.addUserMovie(link);

        assertEquals("link id not inserted", link.getLinkid(), dao.getMovieLink(newMovieLink).getLinkid());
        assertEquals("link movie not inserted", link.getMovieid(), dao.getMovieLink(newMovieLink).getMovieid());
        assertEquals("link quantity not inserted", link.getQuantity(), dao.getMovieLink(newMovieLink).getQuantity());
        assertEquals("link owner not inserted", link.getUserid(), dao.getMovieLink(newMovieLink).getUserid());
        assertEquals("link rating not inserted", link.getStarrating(), dao.getMovieLink(newMovieLink).getStarrating());

        link.setUserid(bob2.getUuid());
        link.setQuantity(3);
        link.setMovieid(film2.getIdmovie());
        link.setStarrating(2);

        dao.updateMovieLink(link);

        assertEquals("link id not updated", link.getLinkid(), dao.getMovieLink(newMovieLink).getLinkid());
        assertEquals("link movie not updated", link.getMovieid(), dao.getMovieLink(newMovieLink).getMovieid());
        assertEquals("link quantity not updated", link.getQuantity(), dao.getMovieLink(newMovieLink).getQuantity());
        assertEquals("link owner not updated", link.getUserid(), dao.getMovieLink(newMovieLink).getUserid());
        assertEquals("link rating not updated", link.getStarrating(), dao.getMovieLink(newMovieLink).getStarrating());
    }

}