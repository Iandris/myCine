package com.mtyoung.Servlet;


import com.mtyoung.com.omdbapi.OmdbJson;
import com.mtyoung.entity.Movie;
import com.mtyoung.persistence.MovieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(
        name = "MovieSearch",
        urlPatterns = { "/secure/auth/moviesearch" }
)
/**
 * Created by Mike on 3/1/17.
 */
public class MovieSearchServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();
        MovieDao movieDao = new MovieDao();
        OmdbJson search = new OmdbJson();
        List<Movie> mymovies = new ArrayList<>();
       // Movie[] sortedMovies = null;
        boolean found = false;

        String searchtitle = request.getParameter("title").replace(" ", "%20");

        try {
            search.searchByTitle(searchtitle);

            mymovies = movieDao.getMoviesByTitleSearch(searchtitle);

            if (mymovies.size() > 0) {
                found = true;
            }

//            if (mymovies.size() > 0) {
//                found = true;
//
//                sortedMovies = new Movie[mymovies.size()];
//                int i = 0;
//                for (Movie mvie: mymovies
//                        ) {
//                    sortedMovies[i] = mvie;
//                    i++;
//                }
//
//                //Arrays.sort(sortedMovies, Movie.MovieNameComparator);
//
//
//            }
            session.setAttribute("count", mymovies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("results", found);
        session.setAttribute("mymovies", mymovies);
        getServletContext().getRequestDispatcher("/secure/auth/moviesearch.jsp").forward(request, response);

    }
}
