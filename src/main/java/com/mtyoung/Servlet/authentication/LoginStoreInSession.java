package com.mtyoung.Servlet.authentication;

import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;
import org.apache.catalina.realm.RealmBase;
import org.apache.log4j.Logger;

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
    private UserDao dao = new UserDao();
    private boolean success;
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("j_username");
        String pwd = request.getParameter("j_password");
        success = true;

        User user = dao.getUserByEmail(username);

        if (user == null) {
            session.setAttribute("uname", username);
            success = false;
        } else {
            checkPassword(session, username, pwd, user);
        }

        if (success) {
            session.setAttribute("failure", null);
            String url = "j_security_check?j_username=" + username + "&j_password=" + pwd;
            String redirectURL = response.encodeRedirectURL(url);
            response.sendRedirect(redirectURL);
        } else {
            response.sendRedirect("/mycine/loginfailure");
        }

    }

    private void checkPassword(HttpSession session, String username, String pwd, User user) {
        session.setAttribute("user",user );
        session.setAttribute("uname", user.getUser_name());
        if(!user.getPassword().equals(RealmBase.Digest(pwd,"sha-256", "UTF-8"))) {
            session.setAttribute("uname", username);
            success = false;
        }
    }
}