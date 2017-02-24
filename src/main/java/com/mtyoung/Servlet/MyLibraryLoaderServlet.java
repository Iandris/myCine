package com.mtyoung.Servlet;

import com.mtyoung.entity.Movie;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.persistence.MovieDao;
import com.mtyoung.persistence.UserMovieDao;
import org.apache.log4j.Logger;

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
        name = "LibraryLoader",
        urlPatterns = {"/secure/auth/library"}
)
/**
 * Created by Mike on 2/24/17.
 */
public class MyLibraryLoaderServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        MovieDao dao = new MovieDao();
        UserMovieDao umdao = new UserMovieDao();

        List<UserMovieLink> links = umdao.getMovieLinkByUserID(1);
        List<Movie> movies = new ArrayList<Movie>();

        if (links != null) {
            for (UserMovieLink link : links
                    ) {
                Movie mv = dao.getMovie(link.getLinkid());
                if (mv != null) {
                    movies.add(mv);
                }
            }

            if (movies != null) {
                Movie[] mymovies = new Movie[movies.size()];
                int i = 0;
                for (Movie movie : movies
                        ) {
                    mymovies[i] = movie;
                    i++;
                }

                Arrays.sort(mymovies, Movie.MovieNameComparator);
                session.setAttribute("mymovies", mymovies);
                session.setAttribute("hmmm", "stuff");
            } else {
                session.setAttribute("mymovies", null);

            }
        } else {
            session.setAttribute("mymovies", null);

        }

        getServletContext().getRequestDispatcher("/secure/auth/mylibrary.jsp").forward(request, response);

    }
}
