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
//    private AddressDao addrDao = new AddressDao();
//    private StateDao stateDao = new StateDao();
    private UserDao dao = new UserDao();
    private User newUser = new User();
//    private UserRoleDao roleDao = new UserRoleDao();
//    private UserRole role = new UserRole();
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

//    private void updateUser(HttpServletRequest request) {
//        newUser.setFname(request.getParameter("firstname"));
//        newUser.setLname(request.getParameter("lastname"));
//        newUser.setUser_name(request.getParameter("user_name"));
//        newUser.setCellnumber(request.getParameter("cellnumber").replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace(" ", ""));
//        newUser.setReminderthreshold(Integer.parseInt(request.getParameter("reminder")));
//        newUser.setDefaultrentalperiod(Integer.parseInt(request.getParameter("rental")));
//
//        if (!newUser.getPassword().equals(request.getParameter("password"))) {
//            newUser.setPassword(RealmBase.Digest(request.getParameter("password"), "sha-256", "UTF-8"));
//        }
//
//        dao.updateUser(newUser);
//    }
//
//    private void updateAddress(HttpServletRequest request) {
//        Address address = addrDao.getAddress(Integer.parseInt(request.getParameter("addrid")));
//        address.setStreetaddress1(request.getParameter("address1"));
//        address.setStreetaddress2(request.getParameter("address2"));
//        address.setCity(request.getParameter("city"));
//        address.setState(stateDao.getState(Integer.parseInt(request.getParameter("state"))));
//        address.setZipcode(Integer.parseInt(request.getParameter("zip")));
//        addrDao.updateAddress(address);
//    }
//
//    private void updateRole(String username) {
//        role = roleDao.getRoleByUserName(username);
//        if (role != null) {
//            //role.setRole_name("registered-user");
//            role.setuser_name(newUser.getUser_name());
//            roleDao.updateRole(role);
//        }
//    }
//
//    private void checkEmail(String email) throws IOException {
//        User test = dao.getUserByEmail(email);
//
//        if (test != null) {
//            session.setAttribute("updateStatus", "Duplicate Email Found, please try a different username");
//            success = false;
//        }
//    }
//
//    private void checkCell(String cell) throws IOException {
//        User test = dao.getUserByPhone(cell);
//
//        if (test != null) {
//            session.setAttribute("updateStatus", "Duplicate Phone Number Found, please try a different number");
//            success = false;
//        }
//    }
}
