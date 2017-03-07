package com.mtyoung.Servlet;

import com.mtyoung.entity.Friendrequests;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.persistence.FriendrequestsDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(
        name = "AcceptFriendRequest",
        urlPatterns = { "/secure/auth/acceptfriendrequest" }
)

/**
 * Created by Mike on 3/6/17.
 */
public class FriendRequestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        String requestUUID = request.getParameter("reqid");

        FriendrequestsDao dao = new FriendrequestsDao();
        UserDao userDao = new UserDao();
        UserFriendDao friendDao = new UserFriendDao();
        Friendrequests friendrequests = dao.findFriendRequestByHashId(requestUUID);

        if (friendrequests != null) {
            User userA = userDao.getUser(friendrequests.getUsera());
            User userB = userDao.getUser(friendrequests.getUserb());

            if (userA != null && userB != null) {
                UserFriends friends = new UserFriends();
                friends.setFrienda(userA.getUuid());
                friends.setFriendb(userB.getUuid());

                int friendID = friendDao.addFriend(friends);
                if (friendID > 0) {
                    dao.deleteFriendRequest(friendrequests.getIdfriendrequests());
                    response.sendRedirect("/mycine/secure/auth/friends");
                } else {
                    response.sendRedirect("/mycine/secure/auth/home");
                }
            }
        }
    }
}
