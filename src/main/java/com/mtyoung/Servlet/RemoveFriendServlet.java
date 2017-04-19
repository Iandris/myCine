package com.mtyoung.Servlet;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by Mike on 4/13/17.
 */

@WebServlet(
        name = "RemoveFriend",
        urlPatterns = { "/secure/auth/removefriend" }
)

public class RemoveFriendServlet extends HttpServlet {
    private UserDao userdao = new UserDao();
    private UserFriendDao friendDao = new UserFriendDao();
    private RentalDao rentalDao = new RentalDao();
    private User user;
    private User friend;
    private String error;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        user = (User)session.getAttribute("user");
        friend = userdao.getUser(Integer.parseInt(request.getParameter("friendid")));

        error = "Unable to Remove Friend " + friend.getFname() + " " + friend.getLname();

        if (user != null && friend != null) {
            for (UserFriends link : friendDao.getFriendsByUser(user.getUuid())) {
                if (link.getFrienda() == friend.getUuid() || link.getFriendb() == friend.getUuid()) {

                    //Check for and delete rentals where user own the movie, and delete friend is owner
                    purgeRentalsInLibrary(friend.getRentalset(), user);

                    //Check for and delete any rentals where user is the renter, and friend is owner
                    purgeRentalsInLibrary(user.getRentalset(), friend);

                    //delete friendship connection
                    friendDao.deleteFriend(link.getIdConnector());
                }
            }

            error = "Sucessfully Ended Friendship with " + friend.getFname() + " " + friend.getLname();
            session.setAttribute("deleteStatus", error);
            response.sendRedirect("/mycine/secure/auth/friends");
        } else {
            session.setAttribute("deleteStatus", error);
            response.sendRedirect("/mycine/secure/auth/friends");
        }

    }

    private void purgeRentalsInLibrary(Set<Rental> rentals, User user) {
        for (Rental rent: rentals
             ) {
            if (rent.getMovieid().getUserid().getUuid() == user.getUuid()) {
                rentalDao.deleteRental(rent.getIdrentals());
            }
        }
    }
}
