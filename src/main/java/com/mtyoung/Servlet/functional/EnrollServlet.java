package com.mtyoung.Servlet.functional;


import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.util.UpdateValidator;
import com.mtyoung.util.UserUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "Enroll",
        urlPatterns = { "/enroll" }
)
/**
 * Created by Mike on 2/16/17.
 */
public class EnrollServlet  extends HttpServlet {
    private UserDao dao = new UserDao();
    private User newUser = new User();
    private HttpSession session;
    private boolean success;
    /**
     * doGet method for mycine Enrollredirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        success = true;
        session = request.getSession();

        UpdateValidator validator = new UpdateValidator();
        UserUpdater updater = new UserUpdater();

        if (validator.checkEmail(request.getParameter("user_name"), session) &&
                validator.checkCell(request.getParameter("cellnumber"), session)) {

            Address addr = updater.createAddress(request);

            if (updater.createUser(request, newUser, addr)) {

                updater.createRole(newUser);

                request.login(newUser.getUser_name(), request.getParameter("password"));
                session.setAttribute("user", dao.getUser(newUser.getUuid()));
                session.setAttribute("updateStatus", null);
                response.sendRedirect("/mycine/secure/auth/home");
            } else {
                response.sendRedirect("/mycine/register");
            }
        } else {
            response.sendRedirect("/mycine/register");
        }
    }
}