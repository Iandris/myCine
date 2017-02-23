package com.mtyoung.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "Home",
        urlPatterns = { "/home" }
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

        if (session.getAttribute("user") != null) {

        }

        getServletContext().getRequestDispatcher("/secure/auth/home.jsp").forward(request, response);
        //response.sendRedirect("/home.jsp");
    }
}
