package com.mtyoung.Servlet;

import com.mtyoung.entity.User;

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
 * Created by Mike on 2/28/17.
 */
public class LoginFailureServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("failure", "Username and Password do not match our records. Please try again.");
        response.sendRedirect("/mycine/login.jsp");
    }
}
