package com.mtyoung.Servlet;

import com.mtyoung.entity.State;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();

        UserDao dao = new UserDao();
        StateDao stateDao = new StateDao();

        List<User> users;
        users = dao.getAllUsers();
        System.out.println(users.size());

        List<State> states = stateDao.getAllStates();

        session.setAttribute("people", users);
        session.setAttribute("states", states);
        getServletContext().getRequestDispatcher("/secure/admin/useradmin.jsp").forward(request, response);

    }

}