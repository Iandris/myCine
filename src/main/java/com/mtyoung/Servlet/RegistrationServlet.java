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
    private StateDao dao = new StateDao();
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
        session.setAttribute("states", dao.getAllStates());
        getServletContext().getRequestDispatcher("/registration").forward(request, response);

    }
}