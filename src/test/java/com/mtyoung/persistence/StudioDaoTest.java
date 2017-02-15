package com.mtyoung.persistence;

import com.mtyoung.entity.Studio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class StudioDaoTest {
    Studio std;
    StudioDao dao;
    int newStudio = 0;

    @Before
    public void setup() {
        std = new Studio();
        dao = new StudioDao();

        std.setStudiotitle("Paramount");

    }

    @After
    public void cleanup() {
        if (newStudio != 0) {
            dao.deleteStudio(newStudio);
        }
    }

    @Test
    public void getAllStudios() throws Exception {
        newStudio = dao.addStudio(std);
        List<Studio> studios = dao.getAllStudios();
        assertTrue(studios.size() > 0);
    }

    @Test
    public void getStudio() throws Exception {
        newStudio = dao.addStudio(std);
        assertEquals("incorrect studio ID returned" , std.getIdstudio(), dao.getStudio(newStudio).getIdstudio());
        assertEquals("incorrect studio title returned" , std.getStudiotitle(), dao.getStudio(newStudio).getStudiotitle());
    }

    @Test
    public void addStudio() throws Exception {
        newStudio = dao.addStudio(std);
        assertEquals("incorrect studio ID inserted" , std.getIdstudio(), dao.getStudio(newStudio).getIdstudio());
        assertEquals("incorrect studio title inserted" , std.getStudiotitle(), dao.getStudio(newStudio).getStudiotitle());

    }

    @Test
    public void deleteStudio() throws Exception {
        dao.addStudio(std);
        dao.deleteStudio(std.getIdstudio());
        assertNull("studio not deleted", dao.getStudio(std.getIdstudio()));
    }

    @Test
    public void updateStudio() throws Exception {
        newStudio = dao.addStudio(std);

        std.setStudiotitle("Sony Pictures");

        dao.updateStudio(std);

        assertEquals("studio title not updated" , std.getStudiotitle(), dao.getStudio(newStudio).getStudiotitle());

    }

}