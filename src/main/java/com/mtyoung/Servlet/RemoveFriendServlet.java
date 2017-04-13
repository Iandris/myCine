package com.mtyoung.Servlet;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;
import org.apache.log4j.Logger;

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
    private final Logger log = Logger.getLogger(this.getClass());
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserDao userdao = new UserDao();
        UserFriendDao friendDao = new UserFriendDao();
        RentalDao rentalDao = new RentalDao();

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        User friend = userdao.getUser(Integer.parseInt(request.getParameter("friendid")));

        String error = "Unable to Remove Friend " + friend.getFname() + " " + friend.getLname();;

        if (user != null && friend != null) {
            List<UserFriends> myFriends = friendDao.getFriendsByUser(user.getUuid());

            for (UserFriends link : myFriends) {
                if (link.getFrienda() == friend.getUuid() || link.getFriendb() == friend.getUuid()) {

                    //Check for and delete rentals where I own the movie, and delete friend is owner
                    Set<Rental> rentals = friend.getRentalset();

                    for (Rental rent: rentals
                         ) {
                        if (rent.getMovieid().getUserid().getUuid() == user.getUuid()) {
                            rentalDao.deleteRental(rent.getIdrentals());
                        }
                    }

                    //Check for and delete any rentals where I am the renter, and friend is owner
                    Set<Rental> myRentals = user.getRentalset();

                    for (Rental rent : myRentals) {
                        if (rent.getMovieid().getUserid().getUuid() == friend.getUuid()) {
                            rentalDao.deleteRental(rent.getIdrentals());
                        }
                    }

                    friendDao.deleteFriend(link.getIdConnector());
                    error = "Sucessfully Ended Friendship with " + friend.getFname() + " " + friend.getLname();
                    session.setAttribute("deleteStatus", error);
                    response.sendRedirect("/mycine/secure/auth/friends");
                }
            }
        } else {
            session.setAttribute("deleteStatus", error);
            response.sendRedirect("/mycine/secure/auth/friends");
        }

    }
}
