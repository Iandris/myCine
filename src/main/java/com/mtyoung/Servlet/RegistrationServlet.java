package com.mtyoung.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Registration",
        urlPatterns = { "/Registration" }
)


/**
 * Created by Mike on 2/16/17.
 */
public class RegistrationServlet  extends HttpServlet {
    /**
     * doGet method for MyCine index.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //TODO make this route conditionally, if user exists in db send to Home, else RegistrationServlet
        getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);

    }
}