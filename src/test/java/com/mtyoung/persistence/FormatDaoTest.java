package com.mtyoung.persistence;

import com.mtyoung.entity.Format;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class FormatDaoTest {

    Format form;
    FormatDao dao;
    int newFormat = 0;

    @Before
    public void setup(){
        dao = new FormatDao();
        form = new Format();
        form.setFormattitle("DVD");
    }

    @After
    public void cleanup() {
        if (newFormat != 0) {
            dao.deleteFormat(newFormat);
        }
    }

    @Test
    public void getAllFormats() throws Exception {
        newFormat = dao.addFormat(form);
        List<Format> formats = dao.getAllFormats();
        assertTrue(formats.size() > 0);
    }

    @Test
    public void getFormat() throws Exception {
        newFormat = dao.addFormat(form);
        assertEquals("format ID not returned", form.getIdformat(), dao.getFormat(newFormat).getIdformat());
        assertEquals("format title not returned", form.getFormattitle(), dao.getFormat(newFormat).getFormattitle());
    }

    @Test
    public void addFormat() throws Exception {
        newFormat = dao.addFormat(form);
        assertEquals("format ID not inserted", form.getIdformat(), dao.getFormat(newFormat).getIdformat());
        assertEquals("format title not inserted", form.getFormattitle(), dao.getFormat(newFormat).getFormattitle());
    }

    @Test
    public void deleteFormat() throws Exception {
        dao.addFormat(form);
        dao.deleteFormat(form.getIdformat());
        assertNull("format not deleted", dao.getFormat(form.getIdformat()));
        
    }

    @Test
    public void updateFormat() throws Exception {
        newFormat = dao.addFormat(form);

        form.setFormattitle("BluRay");

        dao.updateFormat(form);

        assertEquals("format title not updated", form.getFormattitle(), dao.getFormat(newFormat).getFormattitle());
    }

}