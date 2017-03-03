package com.mtyoung.Servlet;

import com.mtyoung.entity.*;
import com.mtyoung.persistence.*;
import jersey.repackaged.com.google.common.collect.Lists;

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
    private HttpSession session;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();

        Movie[] mymovies = buildLibrary();

        session.setAttribute("new", null);

        session.setAttribute("mymovies", mymovies);

        getServletContext().getRequestDispatcher("/secure/auth/mylibrary.jsp").forward(request, response);
    }

    public Movie[] buildLibrary() {
        User user = (User)session.getAttribute("user");
        MovieDao dao = new MovieDao();
        UserMovieDao umdao = new UserMovieDao();

        List<UserMovieLink> links = umdao.getMoviesLinkByUserID(user.getUuid());
        Movie[] films = new Movie[links.size()];

        int i =0;
        for (UserMovieLink link: links
                ) {
            films[i] = dao.getMovie(link.getMovieid().getIdmovie());
            i++;
        }

        Arrays.sort(films, Movie.MovieNameComparator);
        return films;
    }
}
