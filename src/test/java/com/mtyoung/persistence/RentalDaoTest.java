package com.mtyoung.persistence;

import com.mtyoung.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class RentalDaoTest {

    UserRoleDao roleDao;
    UserRole role;
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
    Rental rental;
    RentalDao dao;
    UserMovieLink movieLink;
    UserMovieDao movieLinkDao;

    int newRole = 0;
    int newDir = 0;
    int newGen = 0;
    int newForm = 0;
    int newStudio = 0;
    int newMovie1 = 0;
    int newMovie2 = 0;
    int newMail = 0;
    int newUser1 = 0;
    int newUser2 = 0;
    int newRental = 0;
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

        role = new UserRole();
        roleDao = new UserRoleDao();
        role.setDescription("CHIPS n SALSA");
        newRole = roleDao.addRole(role);

        userDao = new UserDao();
        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setRoleid(newRole);
        bob1.setAddressid(newMail);
        bob1.setEmail("mtyoung@madisoncollege.edu");
        bob1.setCellnumber("6083334717");
        bob1.setPassword("Password");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("Michael");
        bob2.setLname("Smith");
        bob2.setRoleid(newRole);
        bob2.setAddressid(newMail);
        bob2.setEmail("myoung86@charter.net");
        bob2.setCellnumber("9202855911");
        bob2.setPassword("Connor");
        bob2.setReminderthreshold(2);
        bob2.setDefaultrentalperiod(5);
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

        movieLinkDao = new UserMovieDao();
        movieLink = new UserMovieLink();
        movieLink.setUserid(bob1.getUuid());
        movieLink.setQuantity(1);
        movieLink.setMovieid(film1.getIdmovie());
        movieLink.setStarrating(5);
        newMovieLink = movieLinkDao.addUserMovie(movieLink);

        dao = new RentalDao();
        rental = new Rental();
        rental.setRenterid(bob1.getUuid());
        rental.setMovieid(movieLink.getLinkid());
        rental.setDuedate(LocalDateTime.of(2017,3,1,10,0,0));
    }

    @After
    public void tearDown() throws Exception {


        if (newRental != 0) {
            dao.deleteRental(newRental);
        }

        if (newMovieLink != 0) {
            movieLinkDao.deleteMovieLink(newMovieLink);
        }

        if (newUser2 != 0) {
            userDao.deleteUser(newUser2);
        }

        if (newUser1 != 0) {
            userDao.deleteUser(newUser1);
        }

        if (newRole != 0) {
            roleDao.deleteRole(newRole);
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
    public void getAllRentals() throws Exception {
        newRental = dao.addRental(rental);
        List<Rental> rentals = dao.getAllRentals();
        assertTrue("no rentals returned", rentals.size() > 0);
    }

    @Test
    public void getRental() throws Exception {
        newRental = dao.addRental(rental);
        assertEquals("rental id not returned", rental.getIdrentals(), dao.getRental(newRental).getIdrentals());
        assertEquals("rental due date not returned", rental.getDuedate(), dao.getRental(newRental).getDuedate());
        assertEquals("rental movie id not returned", rental.getMovieid(), dao.getRental(newRental).getMovieid());
        assertEquals("rental renter id not returned", rental.getRenterid(), dao.getRental(newRental).getRenterid());
    }

    @Test
    public void addRental() throws Exception {
        newRental = dao.addRental(rental);
        assertEquals("rental id not inserted", rental.getIdrentals(), dao.getRental(newRental).getIdrentals());
        assertEquals("rental due date not inserted", rental.getDuedate(), dao.getRental(newRental).getDuedate());
        assertEquals("rental movie id not inserted", rental.getMovieid(), dao.getRental(newRental).getMovieid());
        assertEquals("rental renter id not inserted", rental.getRenterid(), dao.getRental(newRental).getRenterid());
    }

    @Test
    public void deleteRental() throws Exception {
        dao.addRental(rental);
        dao.deleteRental(rental.getIdrentals());
        assertNull("rental not deleted", dao.getRental(newRental));
    }

    @Test
    public void updateRental() throws Exception {
        newRental = dao.addRental(rental);

        rental.setRenterid(bob2.getUuid());
        rental.setMovieid(movieLink.getLinkid());
        rental.setDuedate(LocalDateTime.of(2018,5,30,9,0,0));

        dao.updateRental(rental);

        assertEquals("rental id not updated", rental.getIdrentals(), dao.getRental(newRental).getIdrentals());
        assertEquals("rental due date not updated", rental.getDuedate(), dao.getRental(newRental).getDuedate());
        assertEquals("rental movie id not updated", rental.getMovieid(), dao.getRental(newRental).getMovieid());
        assertEquals("rental renter id not updated", rental.getRenterid(), dao.getRental(newRental).getRenterid());
    }

}