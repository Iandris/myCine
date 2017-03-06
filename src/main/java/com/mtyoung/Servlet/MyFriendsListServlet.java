package com.mtyoung.Servlet;

import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "MyFriends",
        urlPatterns = { "/secure/auth/friends" }
)

/**
 * Created by Mike on 3/6/17.
 */
public class MyFriendsListServlet extends HttpServlet {
    private HttpSession session;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();

        if(session.getAttribute("friends") != null) {
            session.setAttribute("friends",null);
        }

        if(session.getAttribute("friendRequest") != null) {
            session.setAttribute("friendRequest", null);
        }

        UserFriendDao dao = new UserFriendDao();
        UserDao usrDao = new UserDao();
        User user = (User)session.getAttribute("user");
        List<UserFriends> friends = dao.getFriendsByUser(user.getUuid());
        List<User> myFriends = new ArrayList<>();

        for (UserFriends friend: friends
             ) {
            if (friend.getFrienda() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFriendb()));
            } else if (friend.getFriendb() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFrienda()));
            }
        }

        session.setAttribute("friends", myFriends);
        getServletContext().getRequestDispatcher("/secure/auth/myfriends.jsp").forward(request, response);
    }
}
