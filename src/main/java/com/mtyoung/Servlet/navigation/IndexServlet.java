package com.mtyoung.Servlet.navigation;


import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "Index",
        urlPatterns = { "/index" }
)

/**
 * Created by Mike on 2/16/17.
 */
public class IndexServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * doGet method for MyCine index.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info(request.getRemoteAddr());
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
