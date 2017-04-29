package com.mtyoung.Servlet.functional;


import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserRole;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserRoleDao;
import com.mtyoung.util.UpdateValidator;
import com.mtyoung.util.UserUpdater;
import org.apache.catalina.realm.RealmBase;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InterruptedIOException;

@WebServlet (
        name = "UpdateUser",
        urlPatterns = {"/secure/admin/updateuser"}
)

/**
 * Created by Mike on 2/23/17.
 */
public class UserAdminUpdateServlet extends HttpServlet {
    private UserDao dao = new UserDao();
    private User newUser = new User();
    private HttpSession session;
    private boolean success;
    private String oldUsername;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UpdateValidator validator = new UpdateValidator();
        UserUpdater updater = new UserUpdater();
        session = request.getSession();
        success = true;

        newUser = dao.getUser(Integer.parseInt(request.getParameter("uuid")));
        oldUsername = newUser.getUser_name();


        if (!oldUsername.equals(request.getParameter("user_name"))) {
            validator.checkEmail(request.getParameter("user_name"), session);
        }

        if (!newUser.getCellnumber().equals(request.getParameter("cellnumber"))) {
            validator.checkCell(request.getParameter("cellnumber"), session);
        }

        updater.updateAddress(request);

        if (success) {

            updater.updateUser(request, newUser);

            updater.updateUserRole(newUser, oldUsername);

        }

        getServletContext().getRequestDispatcher("/secure/admin/useradmin").forward(request, response);
    }
}
