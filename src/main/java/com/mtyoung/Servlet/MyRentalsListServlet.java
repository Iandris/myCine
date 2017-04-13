package com.mtyoung.Servlet;

/**
 * Created by Mike on 4/13/17.
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MyRentals",
        urlPatterns = {"/secure/auth/rentals"}
        )
public class MyRentalsListServlet  extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {





        getServletContext().getRequestDispatcher("/secure/auth/myrentals.jsp").forward(request, response);
    }
}
