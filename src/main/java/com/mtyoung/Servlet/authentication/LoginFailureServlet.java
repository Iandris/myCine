package com.mtyoung.Servlet.authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LoginFailure",
        urlPatterns = { "/loginfailure" }
)

/**
 * LoginFailureServlet - redirect to login.jsp after inserting a failure message into session
 * Created by Mike on 2/28/17.
 */
public class LoginFailureServlet  extends HttpServlet {
    /**
     * doGet method, handles GET request at /loginfailure url, injects login failure message and redirects client
     * browser back to login.jsp
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("failure", "Username and Password do not match our records. Please try again.");
        response.sendRedirect("/mycine/login.jsp");
    }
}
