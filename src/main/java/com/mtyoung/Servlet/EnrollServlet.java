package com.mtyoung.Servlet;


import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;

import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "Enroll",
        urlPatterns = { "/Enroll" }
)
/**
 * Created by Mike on 2/16/17.
 */
public class EnrollServlet  extends HttpServlet {
    /**
     * doGet method for MyCine Enrollredirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();
        UserDao dao = new UserDao();
        User newUser = new User();

        newUser.setFname(request.getParameter("firstname").toString());
        newUser.setLname(request.getParameter("lastname").toString());
        newUser.setRoleid(1);
        newUser.setAddressid(1);
        newUser.setEmail(request.getParameter("email").toString());
        newUser.setCellnumber(request.getParameter("cellnumber").toString());
        newUser.setPassword("Password");
        newUser.setReminderthreshold(1);
        newUser.setDefaultrentalperiod(3);
        newUser.setFirebaseUID(request.getParameter("uid").toString());

        dao.addUser(newUser);

        response.sendRedirect("/myCine/Home");

    }
}