package com.mtyoung.Servlet.authentication;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(
        name = "Logout",
        urlPatterns = { "/logout" }
)

/**
 * Created by Mike on 2/21/17.
 */
public class LogoutServlet  extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();

        session.invalidate();

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
