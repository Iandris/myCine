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

/**
 * Created by Mike on 4/13/17.
 */

@WebServlet(
        name = "Settings",
        urlPatterns = {"/secure/auth/settings"}
)
public class UserSettingsServlet extends HttpServlet {
    private UserDao dao = new UserDao();
    private StateDao stateDao = new StateDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();

        User me = (User)session.getAttribute("user");
        User user = dao.getUser(me.getUuid());

        if (user != null ){
            session.setAttribute("user", user);
        }

        session.setAttribute("states", stateDao.getAllStates());

        getServletContext().getRequestDispatcher("/secure/auth/settings.jsp").forward(request, response);
    }
}
