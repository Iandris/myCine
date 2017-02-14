package com.mtyoung.persistence;

import com.mtyoung.entity.Director;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class DirectorDaoTest {

    DirectorDao dao;
    Director director;
    int newDirector = 0;

    @Before
    public void setup() {
        dao = new DirectorDao();
        director = new Director();
        director.setFname("Martin");
        director.setLname("Scorsese");
    }

    @After
    public void cleanup() {
        if (newDirector != 0) {
            dao.deleteDirector(newDirector);
        }
    }


    @Test
    public void getAllDirectors() throws Exception {
        newDirector = dao.addDirector(director);
        List<Director> directors = dao.getAllDirectors();
        assertTrue(directors.size() > 0);
    }

    @Test
    public void getDirector() throws Exception {
        newDirector = dao.addDirector(director);
        assertEquals("Director ID not returned", director.getIddirector(), dao.getDirector(newDirector).getIddirector());
        assertEquals("FirstName not returned", director.getFname(), dao.getDirector(newDirector).getFname());
        assertEquals("LastName not returned", director.getLname(), dao.getDirector(newDirector).getLname());
    }

    @Test
    public void addDirector() throws Exception {
        newDirector = dao.addDirector(director);
        assertEquals("Director ID not inserted", director.getIddirector(), dao.getDirector(newDirector).getIddirector());
        assertEquals("FirstName not inserted", director.getFname(), dao.getDirector(newDirector).getFname());
        assertEquals("LastName not inserted", director.getLname(), dao.getDirector(newDirector).getLname());

    }

    @Test
    public void deleteDirector() throws Exception {
        dao.addDirector(director);
        dao.deleteDirector(director.getIddirector());
        assertNull("user not deleted", dao.getDirector(director.getIddirector()));
    }

    @Test
    public void updateDirector() throws Exception {
        newDirector = dao.addDirector(director);

        director.setFname("Steven");
        director.setLname("Spielberg");

        dao.updateDirector(director);

        assertEquals("Director ID not updated", director.getIddirector(), dao.getDirector(newDirector).getIddirector());
        assertEquals("FirstName not updated", director.getFname(), dao.getDirector(newDirector).getFname());
        assertEquals("LastName not updated", director.getLname(), dao.getDirector(newDirector).getLname());


    }

}