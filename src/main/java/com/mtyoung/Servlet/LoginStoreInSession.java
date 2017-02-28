package com.mtyoung.Servlet;

import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LoginStore",
        urlPatterns = { "/storelogin" }
)


/**
 * Created by Mike on 2/28/17.
 */
public class LoginStoreInSession  extends HttpServlet  {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("j_username");
        String pwd = request.getParameter("j_password");

        UserDao dao = new UserDao();
        User user = dao.getUserByEmail(username);


        HttpSession session = request.getSession();
        session.setAttribute("user",user );

        String url = "j_security_check?j_username=" + username + "&j_password=" + pwd;
        String redirectURL = response.encodeRedirectURL(url);
        response.sendRedirect(redirectURL);
    }
}
