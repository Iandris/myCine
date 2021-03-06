package com.mtyoung.Servlet.navigation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Mike on 4/15/17.
 */

@WebServlet(
        name = "Support",
        urlPatterns = {"/support"}
)
public class SupportRedirectServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/support.jsp").forward(request, response);

    }
}
