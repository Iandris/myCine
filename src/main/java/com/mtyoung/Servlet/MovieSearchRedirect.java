package com.mtyoung.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "Search",
        urlPatterns = { "/secure/auth/search" }
)
/**
 * Created by Mike on 3/1/17.
 */
public class MovieSearchRedirect extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();
        session.setAttribute("count", null);
        session.setAttribute("mymovies", null);
        session.setAttribute("results", null);
        getServletContext().getRequestDispatcher("/secure/auth/moviesearch.jsp").forward(request, response);
    }
}
