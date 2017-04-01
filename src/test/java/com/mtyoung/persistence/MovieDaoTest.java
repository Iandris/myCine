package com.mtyoung.persistence;

import com.mtyoung.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/13/17.
 */
public class MovieDaoTest {

    Address mail;
    AddressDao mailDao;
    UserDao userDao;
    StateDao stateDao;
    Movie film;
    MovieDao dao;
    Genre gen;
    GenreDao genDao;
    Format form;
    FormatDao formDao;
    Director dir;
    DirectorDao dirDao;
    Studio std;
    StudioDao stdDao;
    User bob1;
    Wishlist link;
    WishlistDao wishlistDao;
    UserMovieDao umdao;
    UserMovieLink link2;

    int newDir = 0;
    int newMovie = 0;
    int newGen = 0;
    int newForm = 0;
    int newStudio = 0;
    int newStudio2 = 0;
    int newMail = 0;
    int newUser1 = 0;
    int newWishList = 0;
    int newUserMovie = 0;

    @Before
    public void setup() {
        stateDao = new StateDao();
        //prep address table first, or user insert will fail on constraint
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
        bob1.setUser_name("bob9@email.com");
        bob1.setCellnumber("1111111111");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        bob1.setPassword("Password");
        newUser1 = userDao.addUser(bob1);

        film = new Movie();
        dao = new MovieDao();

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

        film.setTitle("The Matrix");
        film.setReleaseDate(LocalDate.of(1999,3,31));
        film.setFormat(form);
        film.setGenre(gen);
        film.setStudio(std);
        film.setDirector(dir);
        film.setImdbid("098765432");
        film.setUpccode("87654323456");

        wishlistDao = new WishlistDao();
        link = new Wishlist();

        umdao = new UserMovieDao();
        link2 = new UserMovieLink();
    }

    @After
    public void cleanup() {
        if (newUserMovie != 0) {
            umdao.deleteMovieLink(newUserMovie);
        }

        if (newWishList != 0) {
            wishlistDao.deleteWishListItem(newWishList);
        }

        if (newMovie != 0) {
            dao.deleteMovie(newMovie);
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

        if (newStudio2 != 0) {
            stdDao.deleteStudio(newStudio2);
        }

        if (newUser1 != 0) {
            userDao.deleteUser(newUser1);
        }

        if (newMail != 0) {
            mailDao.deleteAddress(newMail);
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
        assertEquals("Director not returned", film.getDirector().getIddirector(), dao.getMovie(newMovie).getDirector().getIddirector());
        assertEquals("Format not returned", film.getFormat().getFormattitle(), dao.getMovie(newMovie).getFormat().getFormattitle());
        assertEquals("Genre not returned", film.getGenre().getGenretitle(), dao.getMovie(newMovie).getGenre().getGenretitle());
        assertEquals("Studio not returned", film.getStudio().getStudiotitle(), dao.getMovie(newMovie).getStudio().getStudiotitle());
        assertEquals("Movie ID not returned", film.getIdmovie(), dao.getMovie(newMovie).getIdmovie());

    }

    @Test
    public void addMovie() throws Exception {
        newMovie = dao.addMovie(film);
        assertNotEquals("no movie added", 0, dao.getMovie(newMovie));
    }

    @Test
    public void deleteMovie() throws Exception {
        dao.addMovie(film);
        dao.deleteMovie(film.getIdmovie());
        assertNull("movie not deleted", dao.getMovie(film.getIdmovie()));
    }

    @Test
    public void updateMovie() throws Exception {
        newMovie = dao.addMovie(film);

        Studio std2 = new Studio();
        std2.setStudiotitle("Sony");
        newStudio2 = stdDao.addStudio(std2);

        film.setTitle("Jaws");
        film.setReleaseDate(LocalDate.of(2005,9,22));
        film.setFormat(form);
        film.setGenre(gen);
        film.setStudio(std);
        film.setDirector(dir);
        film.setImdbid("abcidkas098b");
        film.setUpccode("9999999955559");

        dao.updateMovie(film);
        assertEquals("title not updated correctly", film.getTitle(), dao.getMovie(newMovie).getTitle());
        assertEquals("release date not updated correctly", film.getReleaseDate(), dao.getMovie(newMovie).getReleaseDate());
        assertEquals("imdb id not updated correctly", film.getImdbid(), dao.getMovie(newMovie).getImdbid());
        assertEquals("upc code not updated", film.getUpccode(), dao.getMovie(newMovie).getUpccode());
        assertEquals("Director not updated", film.getDirector().getIddirector(), dao.getMovie(newMovie).getDirector().getIddirector());
        assertEquals("Format not updated", film.getFormat().getFormattitle(), dao.getMovie(newMovie).getFormat().getFormattitle());
        assertEquals("Genre not updated", film.getGenre().getGenretitle(), dao.getMovie(newMovie).getGenre().getGenretitle());
        assertEquals("Studio not updated", film.getStudio().getStudiotitle(), dao.getMovie(newMovie).getStudio().getStudiotitle());
        assertEquals("Movie ID not updated", film.getIdmovie(), dao.getMovie(newMovie).getIdmovie());
    }

    @Test
    public void getRecent() throws Exception {
        newMovie = dao.addMovie(film);
        List<Movie> movies =  dao.getRecentMovies(film.getReleaseDate());
        assertTrue(movies.size() > 0);
    }

    @Test
    public void getMovieByIMDB() throws Exception {
        newMovie = dao.addMovie(film);
        assertEquals("movie not returned by IMDB id", film.getTitle(), dao.getMovieByIMDB(film.getImdbid()).getTitle());
    }

    @Test
    public void getMoviesByTitleSearch() throws Exception {
        newMovie = dao.addMovie(film);
        List<Movie> movies =  dao.getMoviesByTitleSearch(film.getTitle());
        assertTrue("movies not found by title search", movies.size() > 0);
    }

    @Test
    public void getMovieListByLinks() throws Exception{
        newMovie = dao.addMovie(film);

        link2.setUserid(bob1);
        link2.setMovieid(film);
        link2.setQuantity(1);
        link2.setStarrating(5);

        newUserMovie = umdao.addUserMovie(link2);

        List<Movie> movies =  dao.getMovieListByLinks(umdao.getAllMovieLinks());
        assertTrue("no movies returned", movies.size() > 0);
    }

    @Test
    public void getMovieListByWishlist() throws Exception {
        newMovie = dao.addMovie(film);

        link.setUserid(bob1);
        link.setMovieid(film);

        newWishList = wishlistDao.addWishListItem(link);

        List<Movie> movies =  dao.getMovieListByWishlist(wishlistDao.getAllWishListItems());
        assertTrue("no movies returned", movies.size() > 0);
    }
}