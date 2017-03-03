package com.mtyoung.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Error",
        urlPatterns = { "/error" }
)
/**
 * Created by Mike on 3/3/17.
 */
public class ErrorRedirect   extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
