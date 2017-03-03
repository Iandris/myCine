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
public class WishlistDaoTest {

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
    Wishlist link;
    WishlistDao dao;
    StateDao stateDao;

    int newDir = 0;
    int newGen = 0;
    int newForm = 0;
    int newStudio = 0;
    int newMovie1 = 0;
    int newMovie2 = 0;
    int newMail = 0;
    int newUser1 = 0;
    int newUser2 = 0;
    int newWishList = 0;

    @Before
    public void setUp() throws Exception {
        //prep address table first, or user insert will fail on constraint
        stateDao = new StateDao();
        mailDao = new AddressDao();
        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("nnnnn");
        mail.setCity("Watertown");
        mail.setState(stateDao.getState(49));
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);

        userDao = new UserDao();
        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setAddress(mail);
        bob1.setUser_name("bob3@email.com");
        bob1.setCellnumber("8888888888");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        bob1.setPassword("Password");
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("Michael");
        bob2.setLname("Smith");
        bob2.setAddress(mail);
        bob2.setUser_name("bob4@email.com");
        bob2.setCellnumber("7777777777");
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
        film1.setFormat(form);
        film1.setGenre(gen);
        film1.setStudio(std);
        film1.setDirector(dir);
        film1.setImdbid("tt0133093");
        film1.setUpccode("883929454563");
        newMovie1 = movieDao.addMovie(film1);

        film2 = new Movie();
        film2.setTitle("Jaws");
        film2.setReleaseDate(LocalDate.of(2005,9,22));
        film2.setFormat(form);
        film2.setGenre(gen);
        film2.setStudio(std);
        film2.setDirector(dir);
        film2.setImdbid("abcidkasb");
        film2.setUpccode("999999999999");
        newMovie2 = movieDao.addMovie(film2);

        dao = new WishlistDao();
        link = new Wishlist();
        link.setUserid(bob1);
        link.setMovieid(film1);
    }

    @After
    public void tearDown() throws Exception {
        if (newWishList != 0) {
            dao.deleteWishListItem(newWishList);
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
    public void getAllWishListItems() throws Exception {
        newWishList = dao.addWishListItem(link);
        List<Wishlist> wishlistItems = dao.getAllWishListItems();
        assertTrue("no wishlist items returned", wishlistItems.size() > 0);
    }

    @Test
    public void getWishListItem() throws Exception {
        newWishList = dao.addWishListItem(link);
        assertEquals("wishlist id not returned", link.getIdwishlistlink(), dao.getWishListItem(newWishList).getIdwishlistlink());
        assertEquals("wishlist movie not returned", link.getMovieid().getIdmovie(), dao.getWishListItem(newWishList).getMovieid().getIdmovie());
        assertEquals("wishlist owner not returned", link.getUserid().getUuid(), dao.getWishListItem(newWishList).getUserid().getUuid());
    }

    @Test
    public void addWishListItem() throws Exception {
        newWishList = dao.addWishListItem(link);
        assertEquals("wishlist id not inserted", link.getIdwishlistlink(), dao.getWishListItem(newWishList).getIdwishlistlink());
        assertEquals("wishlist movie not inserted", link.getMovieid().getIdmovie(), dao.getWishListItem(newWishList).getMovieid().getIdmovie());
        assertEquals("wishlist owner not inserted", link.getUserid().getUuid(), dao.getWishListItem(newWishList).getUserid().getUuid());
    }

    @Test
    public void deleteWishListItem() throws Exception {
        dao.addWishListItem(link);
        dao.deleteWishListItem(link.getIdwishlistlink());
        assertNull("wishlist link not deleted", dao.getWishListItem(link.getIdwishlistlink()));
    }

    @Test
    public void updateWishListItem() throws Exception {
        newWishList = dao.addWishListItem(link);

        assertEquals("wishlist id not inserted", link.getIdwishlistlink(), dao.getWishListItem(newWishList).getIdwishlistlink());
        assertEquals("wishlist movie not inserted", link.getMovieid().getIdmovie(), dao.getWishListItem(newWishList).getMovieid().getIdmovie());
        assertEquals("wishlist owner not inserted", link.getUserid().getUuid(), dao.getWishListItem(newWishList).getUserid().getUuid());

        link.setUserid(bob2);
        link.setMovieid(film2);

        dao.updateWishListItem(link);

        assertEquals("wishlist id not updated", link.getIdwishlistlink(), dao.getWishListItem(newWishList).getIdwishlistlink());
        assertEquals("wishlist movie not updated", link.getMovieid().getIdmovie(), dao.getWishListItem(newWishList).getMovieid().getIdmovie());
        assertEquals("wishlist owner not updated", link.getUserid().getUuid(), dao.getWishListItem(newWishList).getUserid().getUuid());

    }

    @Test
    public void getWishListByUserID() throws Exception {
        newWishList = dao.addWishListItem(link);
        assertNotNull("no movie returned for user in link", dao.getWishListByUserID(link.getUserid().getUuid()));
        assertNotNull("no movie link returned for user", dao.getWishListByUserID(bob1.getUuid()));
        assertEquals("link count returned different than expected", 1, dao.getWishListByUserID(link.getUserid().getUuid()).size());
    }

    @Test
    public void getLinkByUserMovie() throws Exception {
        newWishList = dao.addWishListItem(link);
        assertNotNull("no links returned", dao.getLinkByUserMovie(link.getUserid().getUuid(), link.getMovieid().getIdmovie()));
    }
}