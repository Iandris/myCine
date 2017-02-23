package com.mtyoung.Servlet;

import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;
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

        HttpSession session  = request.getSession();

        session.setAttribute("users", users);
        log.info("total users found " + users.size());
        getServletContext().getRequestDispatcher("/secure/admin/useradmin.jsp").forward(request, response);

    }
}