package com.mtyoung.Servlet.navigation;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;

import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;
import com.mtyoung.persistence.UserMovieDao;

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
    private UserFriendDao dao;
    private UserDao usrDao;
    private RentalDao rentalDao;
    private UserMovieDao librarydao;
    private ArrayList<User> myFriends;
    private ArrayList<Rental> rentals;
    private User user;
    private List<UserFriends> friends;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        dao = new UserFriendDao();
        usrDao = new UserDao();
        rentalDao = new RentalDao();
        librarydao = new UserMovieDao();
        session = request.getSession();
        myFriends = new ArrayList<>();
        rentals = new ArrayList<>();

        if(session.getAttribute("friends") != null) {
            session.setAttribute("friends",null);
        }

        if(session.getAttribute("friendRequest") != null) {
            session.setAttribute("friendRequest", null);
        }

        user = (User)session.getAttribute("user");
        friends = dao.getFriendsByUser(user.getUuid());

        if (friends != null) {
            buildFriendsList(user, myFriends, friends);

            for (User friend: myFriends
                 ) {
                findRentals(user, rentals, friend);
            }
        }

        session.setAttribute("friends", myFriends);
        session.setAttribute("rentals", rentals);
        getServletContext().getRequestDispatcher("/secure/auth/myfriends.jsp").forward(request, response);
    }

    /**
     * findRentals method, builds the list of rentals based on current user
     * @param user
     * @param rentals
     * @param friend
     */
    private void findRentals(User user, List<Rental> rentals, User friend) {
        for (Rental rent: rentalDao.getRentalsByRenter(friend)
             ) {
            UserMovieLink link = librarydao.getLinkByUserMovie(user.getUuid(), rent.getMovieid().getMovieid().getIdmovie());
            if (link != null) {
                rentals.add(rent);
            }
        }
    }

    /**
     * buildFriendsList method, builds a list of friends for the current user
     * @param user
     * @param myFriends
     * @param friends
     */
    private void buildFriendsList(User user, ArrayList<User> myFriends, List<UserFriends> friends) {
        for (UserFriends friend : friends
                ) {
            if (friend.getFrienda() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFriendb()));
            } else if (friend.getFriendb() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFrienda()));
            }
        }
    }
}
