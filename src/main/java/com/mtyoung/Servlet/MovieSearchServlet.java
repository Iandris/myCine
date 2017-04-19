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
    private MovieDao movieDao = new MovieDao();
    private OmdbJson search = new OmdbJson();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();

        List<Movie> mymovies = new ArrayList<>();
        boolean found = false;

        String searchtitle = request.getParameter("title").replace(" ", "%20");

        try {
            search.searchByTitle(searchtitle);

            mymovies = movieDao.getMoviesByTitleSearch(searchtitle);

            if (mymovies.size() > 0) {
                found = true;
            }
            session.setAttribute("count", mymovies.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("results", found);
        session.setAttribute("mymovies", mymovies);
        getServletContext().getRequestDispatcher("/secure/auth/moviesearch.jsp").forward(request, response);

    }
}
