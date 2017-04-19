package com.mtyoung.Servlet.functional;

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
    private FriendrequestsDao dao = new FriendrequestsDao();
    private UserDao userDao = new UserDao();
    private UserFriendDao friendDao = new UserFriendDao();
    private boolean success;
    private Friendrequests friendrequests;
    private String requestUUID;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        requestUUID = request.getParameter("reqid");
        success = true;
        friendrequests = dao.findFriendRequestByHashId(requestUUID);

        if (friendrequests != null) {
            User userA = userDao.getUser(friendrequests.getUsera());
            User userB = userDao.getUser(friendrequests.getUserb());

            if (userA != null && userB != null) {
                createFriendshipLink(userA, userB);
            }
        }

        if (success) {
            dao.deleteFriendRequest(friendrequests.getIdfriendrequests());
            response.sendRedirect("/mycine/secure/auth/friends");
        } else {
            response.sendRedirect("/mycine/secure/auth/home");
        }
    }

    private void createFriendshipLink(User userA, User userB) {
        UserFriends friends = new UserFriends();
        friends.setFrienda(userA.getUuid());
        friends.setFriendb(userB.getUuid());

        if (friendDao.addFriend(friends) > 0) {
            success = false;
        }
    }
}
