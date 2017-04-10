package com.mtyoung.rest;

import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.MovieDao;
import jersey.repackaged.com.google.common.collect.Maps;

import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mike on 3/7/17.
 */
@Path("/titles")
public class Titles {
    MovieDao dao = new MovieDao();
    List<Movie> titles;

    private void loadlist(String title) {
        titles = dao.getMoviesByTitleSearch(title);
    }

    private void loadlist() {
        titles = dao.getAllMovies();
    }

    private void loadlist(long upc) {
        titles = new ArrayList<Movie>();
        titles.add(dao.getMovieByUPC(String.valueOf(upc)));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/upc/{upc}")
    public Response getTitleByUPC(@PathParam("upc") long upcCode) {
        loadlist(upcCode);
        Map<String, ArrayList<Map>> entity = Maps.newTreeMap();
        ArrayList<Map> movies = new ArrayList<>();
        for (Movie movie : titles
                ) {
            Map<String, String> detail = Maps.newTreeMap();
            detail.put("Movie Title:", movie.getTitle());
            detail.put("IMDB id:", movie.getImdbid());
            movies.add(detail);

        }
        entity.put("Titles", movies);
        return Response.status(200).entity(entity).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces("text/plain")
    public Response getTitles() {
        loadlist();
        String output = "";
        for (Movie movie: titles
                ) {
            output += "\nTitle:\t" + movie.getTitle() + "\tIMDB id:\t" + movie.getImdbid();
        }
        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/text/{name}")
    public Response getTitleAsText(@PathParam("name") String title) {
        loadlist(title);
        String output = "";
        for (Movie movie: titles
             ) {
         output += "\nTitle:\t" + movie.getTitle() + "\tIMDB id:\t" + movie.getImdbid();
        }
        return Response.status(200).entity(output).type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/json/{name}")
    public Response getTitleAsJSON(@PathParam("name") String title) {
        loadlist(title);
        Map<String, ArrayList<Map>> entity = Maps.newTreeMap();
        ArrayList<Map> movies = new ArrayList<>();
        for (Movie movie : titles
             ) {
            Map<String, String> detail = Maps.newTreeMap();
            detail.put("Movie Title:", movie.getTitle());
            detail.put("IMDB id:", movie.getImdbid());
            movies.add(detail);

        }
        entity.put("Titles", movies);
        return Response.status(200).entity(entity).type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/html/{name}")
    public Response getTitleAsHTML(@PathParam("name") String title) {
        loadlist(title);

        String output = "<html><body><h1><table>";
        output += "<tr><th>Movie Titles</th><th>IMDB ids</th></tr>";

        for (Movie movie: titles
             ) {

            output += "<tr><td>" + movie.getTitle() + "</td><td>" + movie.getImdbid() + "</td>";
        }

        output += "</table></h1></body></html>";
        return Response.status(200).entity(output).type(MediaType.TEXT_HTML).build();
    }
}
