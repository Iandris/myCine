package com.mtyoung.Servlet;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.State;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserRole;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserRoleDao;
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
        name = "UserAdmin",
        urlPatterns = { "/secure/admin/useradmin" }
)

/**
 * Created by Mike on 2/23/17.
 */
public class UserAdminServlet  extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        UserDao dao = new UserDao();
        List<User> users = dao.getAllUsers();
        AddressDao addrdao = new AddressDao();
        List<Address> addresses = addrdao.getAllAddresses();
        StateDao stdao = new StateDao();
        List<State> states = stdao.getAllStates();
        UserRoleDao roleDao = new UserRoleDao();
        List<UserRole> roles = roleDao.getAllRoles();

        HttpSession session  = request.getSession();

        session.setAttribute("users", users);
        session.setAttribute("addresses", addresses);
        session.setAttribute("states", states);
        session.setAttribute("roles", roles);
        getServletContext().getRequestDispatcher("/secure/admin/useradmin.jsp").forward(request, response);

    }
}