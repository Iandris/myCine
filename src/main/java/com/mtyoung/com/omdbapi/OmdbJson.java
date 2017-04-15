package com.mtyoung.com.omdbapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtyoung.entity.Director;
import com.mtyoung.entity.Genre;
import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.*;
import com.mtyoung.util.LocalDateAttributeConverter;
import org.apache.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mike on 3/1/17.
 */
public class OmdbJson {
    private final Logger log = Logger.getLogger(this.getClass());
    public void searchByTitle(String movieTitle) throws Exception  {

        MovieDao dao = new MovieDao();
        FormatDao fmdao = new FormatDao();
        GenreDao gnDao = new GenreDao();
        StudioDao studioDao = new StudioDao();
        DirectorDao directorDao = new DirectorDao();
        //List<Movie> found = new ArrayList<>();
        List<Search> searches = new ArrayList<>();
        Client client = ClientBuilder.newClient();
        ObjectMapper mapper = new ObjectMapper();
        LocalDateAttributeConverter converter = new LocalDateAttributeConverter();

        WebTarget target =
                client.target("http://www.omdbapi.com/?r=json&type=movie&s=" + movieTitle);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //Search search = ;

        searches.add(mapper.readValue(response, Search.class));

        int totalresults = Integer.parseInt(searches.get(0).getTotalResults());

        //if (dao.getMoviesByTitleSearch(movieTitle).size() < totalresults ) {

            for (int i = 2; i < ((totalresults / 10) + 1); i++) {
                target =
                        client.target("http://www.omdbapi.com/?r=json&type=movie&s=" + movieTitle + "&page=" + i);
                response = target.request(MediaType.APPLICATION_JSON).get(String.class);

                searches.add(mapper.readValue(response, Search.class));
            }

            for (Search searchers : searches
                    ) {

                for (SearchItem item : searchers.getSearch()
                        ) {

                    if (dao.getMovieByIMDB(item.getImdbID()) == null) {
                        WebTarget target2 =
                                client.target("http://www.omdbapi.com/?r=json&type=movie&plot=long&i=" + item.getImdbID());
                        String response2 = target2.request(MediaType.APPLICATION_JSON).get(String.class);


                        ObjectMapper mapper2 = new ObjectMapper();
                        Title title = mapper2.readValue(response2, Title.class);

                        //Filtering out Adult Genre Results and those with null or unknown release dates
                        if (!title.getGenre().equals("Adult") && !title.getTitle().contains("XXX") &&
                                !title.getReleased().equals("N/A")) {

                            Movie movie = new Movie();
                            movie.setTitle(title.getTitle());
                            movie.setFormat(fmdao.getFormat(1));

                            if (!title.getGenre().equals("N/A")) {
                                if (gnDao.getGenreByTitle(title.getGenre()) == null) {
                                    Genre gn = new Genre();
                                    gn.setGenretitle(title.getGenre());
                                    gnDao.addGenre(gn);
                                    movie.setGenre(gn);
                                } else {
                                    movie.setGenre(gnDao.getGenreByTitle(title.getGenre()));
                                }
                            } else {
                                movie.setGenre(gnDao.getGenre(1));
                            }

                            movie.setStudio(studioDao.getStudio(1));

                            if (!title.getDirector().equals("N/A")) {
                                String[] directors = title.getDirector().split(",");
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
                            } else {
                                movie.setDirector(directorDao.getDirector(1));
                            }

                            movie.setImdbid(title.getImdbID());

                            movie.setReleaseDate(converter.convertFromString(title.getReleased()));

                            if (title.getPoster().equals("N/A")) {
                                movie.setImgsource("/mycine/images/unknown.png");
                            } else {
                                movie.setImgsource(title.getPoster());
                            }

                            int added = dao.addMovie(movie);
                            log.info("HMMM" + movieTitle + " rows added: " + added);
                            //found.add(movie);
                        }

                    }
//                 else {
//                     found.add(dao.getMovieByIMDB(item.getImdbID()));
//                 }
                }
            }
       // }

        //return found;
    }

    public Map<String, String> convertName(String name) throws Exception  {
        Map<String, String> director = new HashMap<>();
        String fname = name.substring(0, name.indexOf(" "));
        String lname = name.substring(name.indexOf(" ") + 1);
        director.put("firstname", fname);
        director.put("lastname", lname);
        return director;
    }
}
