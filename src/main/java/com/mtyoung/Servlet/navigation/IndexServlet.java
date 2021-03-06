package com.mtyoung.Servlet.navigation;


import com.mtyoung.util.EmailMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "Index",
        urlPatterns = { "/landing" }
)

/**
 * Created by Mike on 2/16/17.
 */
public class IndexServlet extends HttpServlet {
    /**
     * doGet method for MyCine lander.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EmailMessage msg = new EmailMessage();
        msg.sendPageAccesser(request.getRemoteAddr());
        getServletContext().getRequestDispatcher("/lander.jsp").forward(request, response);

    }
}
