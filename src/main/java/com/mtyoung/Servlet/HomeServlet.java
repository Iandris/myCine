package com.mtyoung.Servlet;

import com.mtyoung.entity.Movie;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.MovieDao;

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
    /**
     * doGet method for MyCine home.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();
        MovieDao mvdao = new MovieDao();

        List<Movie> movies = mvdao.getRecentMovies(LocalDate.now());
        List<Movie> upcoming = new ArrayList<Movie>();
        List<Movie> newreleases = new ArrayList<Movie>();

        if (movies != null & movies.size() > 0) {
            for (Movie movie: movies
                    ) {
                if (movie.getReleaseDate().isAfter(LocalDate.now()) ) {
                    upcoming.add(movie);
                } else {
                    newreleases.add(movie);
                }
            }

            session.setAttribute("newreleases" ,newreleases );
            session.setAttribute("upcoming" ,upcoming );
        }

        getServletContext().getRequestDispatcher("/secure/auth/home.jsp").forward(request, response);
    }
}
