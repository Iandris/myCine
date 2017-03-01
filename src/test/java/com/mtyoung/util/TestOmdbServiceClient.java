package com.mtyoung.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtyoung.com.omdbapi.OmdbJson;
import com.mtyoung.com.omdbapi.Search;
import com.mtyoung.com.omdbapi.SearchItem;
import com.mtyoung.com.omdbapi.Title;
import com.mtyoung.entity.Director;
import com.mtyoung.entity.Genre;
import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.*;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Mike on 3/1/17.
 */
public class TestOmdbServiceClient {

    String movieTitle;
    String someNames;
    String someName;
    OmdbJson myJson;

    @Before
    public void setup() {
        movieTitle = "The%20Matrix";
        someNames = "Lana Wachowski, Lilly Wachowski";
        someName = "Lilly Wachowski";
        myJson = new OmdbJson();
    }

    private final Logger log = Logger.getLogger(this.getClass());
    @Test
    public void testOMDBAPIJSON() throws Exception {
        assertNotEquals("no movies found", 0, myJson.searchByTitle(movieTitle).size());
    }

    @Test
    public void convertName() throws Exception  {
        Map<String, String> dir = myJson.convertName(someName);
        assertEquals("firstname incorrect", "Lilly", dir.get("firstname"));
        assertEquals("firstname incorrect", "Wachowski", dir.get("lastname"));
    }

}
