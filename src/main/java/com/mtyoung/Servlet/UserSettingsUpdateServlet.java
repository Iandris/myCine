package com.mtyoung.Servlet;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserRole;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserRoleDao;
import org.apache.catalina.realm.RealmBase;

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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();

        AddressDao addrDao = new AddressDao();
        StateDao stateDao = new StateDao();
        UserDao dao = new UserDao();
        UserRoleDao roleDao = new UserRoleDao();


        User me = (User)session.getAttribute("user");
        User user = dao.getUser(me.getUuid());

        Address addr = addrDao.getAddress(Integer.parseInt(request.getParameter("addrid")));

        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(stateDao.getState(Integer.parseInt(request.getParameter("state"))));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        addrDao.updateAddress(addr);


        User newUser = dao.getUser(Integer.parseInt(request.getParameter("uuid")));
        String username = newUser.getUser_name();

        newUser.setFname(request.getParameter("firstname"));
        newUser.setLname(request.getParameter("lastname"));

        newUser.setUser_name(request.getParameter("user_name"));

        if (!newUser.getUser_name().equals(user.getUser_name())) {

            User emlusr = dao.getUserByEmail(newUser.getUser_name());

            if (emlusr != null) {
                session.setAttribute("updateStatus", "Unable to Update, Duplicate UserName/Email Found");
                response.sendRedirect("/mycine/secure/auth/settings");
            }
        }

        newUser.setCellnumber(request.getParameter("cellnumber").replace(".","").replace("-","").replace("(","").replace(")","").replace(" ",""));

        if (!newUser.getCellnumber().equals(user.getCellnumber())) {

            User usr = dao.getUserByPhone(newUser.getCellnumber());

            if (usr != null) {
                session.setAttribute("updateStatus", "Unable to Update, Duplicate Cell Number Found");
                response.sendRedirect("/mycine/secure/auth/settings");
            }
        }

        newUser.setReminderthreshold(Integer.parseInt(request.getParameter("reminder")));
        newUser.setDefaultrentalperiod(Integer.parseInt(request.getParameter("rental")));

        if (!newUser.getPassword().equals(request.getParameter("password"))) {
            newUser.setPassword(RealmBase.Digest(request.getParameter("password"),"sha-256", "UTF-8"));
        }

        dao.updateUser(newUser);

        UserRole role = roleDao.getRoleByUserName(username);

        if (role != null) {
            role.setuser_name(newUser.getUser_name());
            roleDao.updateRole(role);
        }

        session.setAttribute("updateStatus", "Settings Update Successful");
        response.sendRedirect("/mycine/secure/auth/settings");

    }
}
