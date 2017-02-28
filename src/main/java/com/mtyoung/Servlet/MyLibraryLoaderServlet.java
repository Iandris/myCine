package com.mtyoung.Servlet;

import com.mtyoung.entity.*;
import com.mtyoung.persistence.*;
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
        User user = (User)session.getAttribute("user");

        MovieDao dao = new MovieDao();
        UserMovieDao umdao = new UserMovieDao();

        List<UserMovieLink> links = umdao.getMovieLinkByUserID(user.getUuid());
        List<Movie> mymovies = new ArrayList<Movie>();

        for (UserMovieLink link: links
             ) {
            mymovies.add(dao.getMovie(link.getMovieid()));
        }

        session.setAttribute("mymovies", mymovies);

        getServletContext().getRequestDispatcher("/secure/auth/mylibrary.jsp").forward(request, response);

    }
}
