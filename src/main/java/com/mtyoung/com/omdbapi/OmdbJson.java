package com.mtyoung.com.omdbapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtyoung.entity.Director;
import com.mtyoung.entity.Genre;
import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.*;
import com.mtyoung.util.LocalDateAttributeConverter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OmdbJason class, used to consume API from omdbapi.com
 * part of mycine project
 * Author: Mike Young
 * Modified: 4/18/2017
 */
public class OmdbJson {
    private static final String API_BASE_PATH = "http://www.omdbapi.com/?r=json&type=movie&s=";
    private static final String API_DETAIL_PATH = "http://www.omdbapi.com/?r=json&type=movie&plot=long&i=";
    private MovieDao dao = new MovieDao();
    private FormatDao fmdao = new FormatDao();
    private GenreDao gnDao= new GenreDao();
    private StudioDao studioDao= new StudioDao();
    private DirectorDao directorDao = new DirectorDao();
    private List<Search> searches = new ArrayList<>();;
    private Client client = ClientBuilder.newClient();
    private ObjectMapper mapper= new ObjectMapper();
    private LocalDateAttributeConverter converter= new LocalDateAttributeConverter();

    /**
     * searchByTitle method, main entry point for OmdbJson.java class, requires String parameter for movie title to search
     * @param movieTitle
     * @throws Exception
     */
    public void searchByTitle(String movieTitle) throws Exception  {
        searches.add(mapper.readValue(getJsonResponse(API_BASE_PATH, movieTitle), Search.class));

        int totalResults = Integer.parseInt(searches.get(0).getTotalResults());

        for (int i = 2; i < ((totalResults / 10) + 1); i++) {
            searches.add(mapper.readValue(getJsonResponse(API_BASE_PATH, movieTitle + "&page=" + i), Search.class));
        }

        for (Search searchers : searches
                ) {
            searchLoop(searchers);
        }
    }

    /**
     * searchLoop method, checks for an existing Movie entity in local db, if not found, creates a new one by mapping
     * the attributes of SearchItem to Movie
     * @param searchers
     * @throws Exception
     */
    private void searchLoop(Search searchers) throws Exception {
        for (SearchItem item : searchers.getSearch()
                ) {

            if (dao.getMovieByIMDB(item.getImdbID()) == null) {

                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Title title = mapper.readValue(getJsonResponse(API_DETAIL_PATH, item.getImdbID()), Title.class);

                    if (isTitleValid(title)) {

                        Movie movie = new Movie();

                        movie.setTitle(title.getTitle());

                        movie.setFormat(fmdao.getFormat(1));

                        movie.setGenre(lookupGenre(title));

                        movie.setStudio(studioDao.getStudio(1));

                        movie.setDirector(lookupDirector(title));

                        movie.setImdbid(title.getImdbID());

                        movie.setReleaseDate(converter.convertFromString(title.getReleased()));

                        movie.setImgsource(lookupMoviePoster(title));

                        dao.addMovie(movie);
                    }
                } catch (SocketTimeoutException timeout) {
                    timeout.printStackTrace();
                }
            }
        }
    }

    /**
     * getJsonResponse method, actual api consuming call, requires 2 String parameters, api path, and parameter
     * @param path
     * @param parameter
     * @return
     */
    private String getJsonResponse(String path, String parameter) {
        WebTarget target =
                client.target(path + parameter);
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    /**
     * isTitleValid method, called to calidate if movie should be added to database, currently filtering out titles with
     * Adult or XXX genres/titles or where release date is NA - returns boolean value for validity
     * @param movie
     * @return
     */
    private Boolean isTitleValid(Title movie) {
        //Filtering out Adult Genre Results and those with null or unknown release dates

        Boolean valid = true;

        if (movie.getGenre().equals("Adult")) {
            valid = false;
        }
        if (movie.getTitle().contains("XXX")) {
            valid = false;
        }
        if (movie.getReleased().equals("N/A")) {
            valid = false;
        }
        return valid;

    }

    /**
     * lookupGenre method, parses out the genre value from found movie, if no genre entity exists in db one is created,
     * returns a Genre entity for attribute mapping in Movie
     * @param title
     * @return
     * @throws Exception
     */
    private Genre lookupGenre(Title title) throws Exception {
        if (!title.getGenre().equals("N/A")) {
            if (gnDao.getGenreByTitle(title.getGenre()) == null) {
                Genre gn = new Genre();
                gn.setGenretitle(title.getGenre());
                gnDao.addGenre(gn);
                return gn;
            } else {
                return gnDao.getGenreByTitle(title.getGenre());
            }
        } else {
            return gnDao.getGenre(1);
        }
    }

    /**
     * lookupMoviePoster method, checks validity of movie poster, if search result is NA for movie poster, returns string
     * of a placeholder on website
     * @param title
     * @return
     * @throws Exception
     */
    private String lookupMoviePoster(Title title) throws Exception {
        if (title.getPoster().equals("N/A")) {
            return "/mycine/images/unknown.png";
        } else {
            return title.getPoster();
        }
    }

    /**
     * lookupDirector method, parses out the director value from the found movie, if no director entity exists in db
     * one is created, returns a Director entity for attribute mapping in Movie
     * @param title
     * @return
     * @throws Exception
     */
    private Director lookupDirector(Title title)  throws Exception  {
        if (!title.getDirector().equals("N/A")) {
            String[] directors = title.getDirector().split(",");
            Map<String, String> dirDetail = convertName(directors[0]);
            String fname = dirDetail.get("firstname");
            String lname = dirDetail.get("lastname");

            if (directorDao.getDirectorByLastFirst(lname, fname) == null) {
                Director newDir = new Director();
                newDir.setFname(fname);
                newDir.setLname(lname);
                directorDao.addDirector(newDir);
                return newDir;
            } else {
                return directorDao.getDirectorByLastFirst(lname, fname);
            }
        } else {
            return (directorDao.getDirector(1));
        }
    }

    /**
     * convertName method, parses the Maps of Directors in json response from api, to director objects.
     * @param name
     * @return
     * @throws Exception
     */
    public Map<String, String> convertName(String name) throws Exception  {
        Map<String, String> director = new HashMap<>();
        String fname = name.substring(0, name.indexOf(" "));
        String lname = name.substring(name.indexOf(" ") + 1);
        director.put("firstname", fname);
        director.put("lastname", lname);
        return director;
    }
}
