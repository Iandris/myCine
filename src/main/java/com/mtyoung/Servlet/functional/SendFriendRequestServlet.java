package com.mtyoung.Servlet.functional;

import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;
import com.mtyoung.util.EmailMessage;

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
        name = "SendFriendRequest",
        urlPatterns = { "/secure/auth/sendfriendrequest" }
)

/**
 * Created by Mike on 3/6/17.
 */
public class SendFriendRequestServlet extends HttpServlet {
    private UserDao userDao;
    private UserFriendDao userFriendDao;
    private String sendMessage;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        userDao = new UserDao();
        userFriendDao = new UserFriendDao();

        if(session.getAttribute("friendRequest") != null) {
            session.setAttribute("friendRequest", null);
        }

        User user = (User)session.getAttribute("user");
        List<String> myFriends = getFriends(user);
        String recipient = request.getParameter("user_name");
        User friend = userDao.getUserByEmail(recipient);

        if (friend != null) {
            if (!myFriends.contains(recipient)) {
                if (recipient.equals(user.getUser_name())) {
                    sendMessage = "Cannot send friend request to yourself.";
                } else {
                    sendFriendRequestMessage(user, recipient, friend);
                }
            } else {
                sendMessage = recipient + " is already in your friends list.";
            }
        } else {
            sendMessage = recipient + " is not registered as a MyCine user. Unable to send friend request.";
        }

        session.setAttribute("friendRequest", sendMessage);

        getServletContext().getRequestDispatcher("/secure/auth/myfriends.jsp").forward(request, response);
    }

    private void sendFriendRequestMessage(User user, String recipient, User friend) {
        EmailMessage email = new EmailMessage();

        boolean success = email.sendFriendRequest(user, friend);

        if (success) {
            sendMessage = "Friend Request Email was sent to " + recipient;
        } else {
            sendMessage = "Friend Request Email failed to send to " + recipient;
        }
    }

    private List<String> getFriends(User user) {

        List<UserFriends> friends = userFriendDao.getFriendsByUser(user.getUuid());
        List<String> myFriends = new ArrayList<>();

        if (friends != null) {
            for (UserFriends friend : friends
                    ) {
                if (friend.getFrienda() == user.getUuid()) {
                    myFriends.add(userDao.getUser(friend.getFriendb()).getUser_name());
                } else if (friend.getFriendb() == user.getUuid()) {
                    myFriends.add(userDao.getUser(friend.getFrienda()).getUser_name());
                }
            }
        }

        return myFriends;
    }
}
