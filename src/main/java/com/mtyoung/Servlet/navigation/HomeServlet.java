package com.mtyoung.Servlet.navigation;

import com.mtyoung.entity.Movie;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.MovieDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "Home",
        urlPatterns = { "/secure/auth/home" }
)

public class HomeServlet  extends HttpServlet {
    private  MovieDao mvdao = new MovieDao();
    private List<Movie> upcoming;
    private List<Movie> newreleases;

    /**
     * doGet method for MyCine home.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("admin", request.isUserInRole("administrator"));
        List<Movie> movies = mvdao.getRecentMovies(LocalDate.now());
        upcoming = new ArrayList<>();
        newreleases = new ArrayList<>();

        if (!movies.isEmpty()) {
            for (Movie movie: movies
                    ) {
                sendMovieToList(movie);
            }

            session.setAttribute("newreleases" ,newreleases );
            session.setAttribute("upcoming" ,upcoming );
        }

        getServletContext().getRequestDispatcher("/secure/auth/home.jsp").forward(request, response);
    }

    private void sendMovieToList(Movie movie) {
        if (movie.getReleaseDate().isAfter(LocalDate.now()) ) {
            upcoming.add(movie);
        } else {
            newreleases.add(movie);
        }
    }
}
