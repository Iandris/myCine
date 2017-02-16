package com.mtyoung.Servlet;

import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RegCheck",
        urlPatterns = { "/RegCheck" }
)

/**
 * Created by Mike on 2/16/17.
 */
public class RegistrationCheckServlet  extends HttpServlet {
    /**
     * doGet method for MyCine index.jsp redirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDao dao = new UserDao();
        User user = dao.getUserByFBUID(request.getParameter("fbUUID"));

        if (user == null) {
            response.sendRedirect("/myCine/Registration");
        } else {
            response.sendRedirect("/myCine/Home");
        }

    }
}