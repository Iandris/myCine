package com.mtyoung.util;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtyoung.com.omdbapi.Search;
import com.mtyoung.com.omdbapi.SearchItem;
import com.mtyoung.com.omdbapi.Title;
import com.mtyoung.entity.Director;
import com.mtyoung.entity.Genre;
import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.*;
import org.apache.log4j.Logger;
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

    String movieTitle = "The%20Matrix";
    String someNames = "Lana Wachowski, Lilly Wachowski";
    String someName = "Lilly Wachowski";

    private final Logger log = Logger.getLogger(this.getClass());
    @Test
    public void testOMDBAPIJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("http://www.omdbapi.com/?r=json&type=movie&s=" + movieTitle);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        assertNotEquals("???", response);

        ObjectMapper mapper = new ObjectMapper();
        Search search = mapper.readValue(response, Search.class);

        MovieDao dao = new MovieDao();
        FormatDao fmdao = new FormatDao();
        GenreDao gnDao = new GenreDao();
        StudioDao studioDao = new StudioDao();
        DirectorDao directorDao = new DirectorDao();
        LocalDateAttributeConverter converter = new LocalDateAttributeConverter();

        assertNotEquals("no titles returned from json", 0, search.getSearch().size());

        for (SearchItem item: search.getSearch()
             ) {

            if (dao.getMovieByIMDB(item.getImdbID()) == null) {
                WebTarget target2 =
                        client.target("http://www.omdbapi.com/?r=json&type=movie&plot=long&i=" + item.getImdbID());
                String response2 = target2.request(MediaType.APPLICATION_JSON).get(String.class);

                assertNotEquals("???", response2);

                ObjectMapper mapper2 = new ObjectMapper();
                Title title = mapper2.readValue(response2, Title.class);

                Movie movie = new Movie();
                movie.setTitle(title.getTitle());
                movie.setFormat(fmdao.getFormat(1));

                if (gnDao.getGenreByTitle(title.getGenre()) == null) {
                    Genre gn = new Genre();
                    gn.setGenretitle(title.getGenre());
                    gnDao.addGenre(gn);
                    movie.setGenre(gn);
                } else {
                    movie.setGenre(gnDao.getGenreByTitle(title.getGenre()));
                }

                movie.setStudio(studioDao.getStudio(1));

                String[] directors = title.getDirector().split(",");

                //TODO currently only grabs the first director, could be multiples
                Map<String, String> dir = convertName(directors[0].toString());
                String fname = dir.get("firstname");
                String lname = dir.get("lastname");

                if (directorDao.getDirectorByLastFirst(lname, fname) == null) {
                    Director newDir = new Director();
                    newDir.setFname(fname);
                    newDir.setLname(lname);
                    directorDao.addDirector(newDir);
                    movie.setDirector(newDir);
                } else {
                    movie.setDirector(directorDao.getDirectorByLastFirst(lname, fname));
                }

                movie.setImdbid(title.getImdbID());

                movie.setReleaseDate(converter.convertFromString(title.getReleased()));
                movie.setImgsource(title.getPoster());

                dao.addMovie(movie);

            } else {

            }

        }

        assertNotEquals("no movies added", 0, dao.getAllMovies().size());
    }

    public Map<String, String> convertName(String name) {
        Map<String, String> director = new HashMap<>();
        String fname = name.substring(0, name.indexOf(" "));
        String lname = name.substring(name.indexOf(" ") + 1);
        director.put("firstname", fname);
        director.put("lastname", lname);
        return director;
    }

    @Test
    public void convertName() {
        Map<String, String> dir = convertName(someName);
        assertEquals("firstname incorrect", "Lilly", dir.get("firstname"));
        assertEquals("firstname incorrect", "Wachowski", dir.get("lastname"));
    }

}
