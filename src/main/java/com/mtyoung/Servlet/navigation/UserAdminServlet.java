package com.mtyoung.Servlet.navigation;

import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "UserAdmin",
        urlPatterns = { "/secure/admin/useradmin" }
)

/**
 * Created by Mike on 2/23/17.
 */
public class UserAdminServlet  extends HttpServlet {
    private UserDao dao = new UserDao();
    private StateDao stateDao = new StateDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session  = request.getSession();

        session.setAttribute("people", dao.getAllUsers());
        session.setAttribute("states", stateDao.getAllStates());
        getServletContext().getRequestDispatcher("/secure/admin/useradmin.jsp").forward(request, response);

    }

}