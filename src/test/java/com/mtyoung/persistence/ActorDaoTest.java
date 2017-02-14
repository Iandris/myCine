package com.mtyoung.persistence;

import com.mtyoung.entity.Actor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class ActorDaoTest {

    ActorDao dao;
    Actor star;
    int newStar = 0;

    @Before
    public void setup() {
        dao = new ActorDao();
        star = new Actor();

        star.setFname("Keanu");
        star.setLname("Reeves");

    }

    @After
    public void cleanup() {
        if (newStar != 0) {
            dao.deleteActor(newStar);
        }
    }

    @Test
    public void getAllMovies() throws Exception {
        newStar = dao.addActor(star);
        List<Actor> stars = dao.getAllActors();
        assertTrue(stars.size() > 0);
    }

    @Test
    public void getActor() throws Exception {
        newStar = dao.addActor(star);
        assertNotEquals("user not added", 0, newStar);
        assertEquals("actor first name not returned", star.getFname(), dao.getActor(newStar).getFname());
        assertEquals("actor last name not returned", star.getLname(), dao.getActor(newStar).getLname());
        assertEquals("actor ID not returned", star.getIdactor(), dao.getActor(newStar).getIdactor());
    }

    @Test
    public void addActor() throws Exception {
        newStar = dao.addActor(star);
        assertNotEquals("user not added", 0, newStar);
        assertEquals("actor first name not added", star.getFname(), dao.getActor(newStar).getFname());
        assertEquals("actor last name not added", star.getLname(), dao.getActor(newStar).getLname());
        assertEquals("actor ID not added", star.getIdactor(), dao.getActor(newStar).getIdactor());
    }

    @Test
    public void deleteActor() throws Exception {
        dao.addActor(star);
        dao.deleteActor(star.getIdactor());
        assertNull("Actor not deleted", dao.getActor(star.getIdactor()));

    }

    @Test
    public void updateActor() throws Exception {
        newStar = dao.addActor(star);

        star.setFname("Woody");
        star.setLname("Haralson");

        dao.updateActor(star);

        assertEquals("actor first name not updated", star.getFname(), dao.getActor(newStar).getFname());
        assertEquals("actor last name not updated", star.getLname(), dao.getActor(newStar).getLname());
    }

}