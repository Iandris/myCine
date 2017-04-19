package com.mtyoung.Servlet.functional;

import com.mtyoung.entity.User;
import com.mtyoung.util.UpdateValidator;
import com.mtyoung.util.UserUpdater;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mike on 4/13/17.
 */


@WebServlet(
        name = "SettingsUpdate",
        urlPatterns = {"/secure/auth/updateuser"}
)
public class UserSettingsUpdateServlet  extends HttpServlet{

    private HttpSession session;
    private String oldUsername;
    private String oldPhone;
    private User user;
    private boolean success;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UpdateValidator validator = new UpdateValidator();
        UserUpdater updater = new UserUpdater();
        session  = request.getSession();
        success = true;

        user = (User) session.getAttribute("user");

        oldUsername = user.getUser_name();
        oldPhone = user.getCellnumber();


        if (!oldUsername.equals(request.getParameter("user_name"))) {
            success = validator.checkEmail(request.getParameter("user_name"), session);
        }

        if (!oldPhone.equals(request.getParameter("cellnumber"))) {
            success = validator.checkCell(request.getParameter("cellnumber"), session);
        }

        if (success) {

            updater.updateAddress(request);

            updater.updateUser(request, user);

            updater.updateUserRole(user, oldUsername);
            session.setAttribute("updateStatus", "Settings Update Successful");
        }

        response.sendRedirect("/mycine/secure/auth/settings");

    }
}
