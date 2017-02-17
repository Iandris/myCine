package com.mtyoung.Servlet;

import com.mtyoung.entity.State;
import com.mtyoung.persistence.StateDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.List;

@WebServlet(
        name = "Register",
        urlPatterns = { "/register" }
)


/**
 * Created by Mike on 2/16/17.
 */
public class RegistrationServlet  extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());
    /**
     * doGet method for MyCine registration.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();
        StateDao dao = new StateDao();
        List<State> states = dao.getAllStates();

        if (states.size() < 1) {
            getServletContext().getRequestDispatcher("/home").forward(request, response);
        } else {
            session.setAttribute("states", states);
            getServletContext().getRequestDispatcher("/registration").forward(request, response);
        }

    }
}