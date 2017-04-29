package com.mtyoung.Servlet.navigation;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Privacy",
        urlPatterns = { "/privacy" }
)

/**
 * Created by Mike on 4/29/17.
 */
public class PrivacyPolicyServlet  extends HttpServlet {
    /**
     * doGet method for MyCine index.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/privacy.jsp").forward(request, response);

    }
}
