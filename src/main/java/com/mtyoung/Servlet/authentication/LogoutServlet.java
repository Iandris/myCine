package com.mtyoung.Servlet.authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "Logout",
        urlPatterns = { "/logout" }
)

/**
 * LogoutServlet - handles naviation to /logout url pattern
 * Created by Mike on 2/21/17.
 */
public class LogoutServlet  extends HttpServlet {

    /**
     * doGET method hands GET request to /logout url pattern, invalidates user session and redirects back to index.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();

        session.invalidate();

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
