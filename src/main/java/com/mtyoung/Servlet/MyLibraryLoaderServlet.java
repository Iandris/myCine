package com.mtyoung.Servlet;

import com.mtyoung.entity.*;
import com.mtyoung.persistence.*;
import jersey.repackaged.com.google.common.collect.Lists;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(
        name = "LibraryLoader",
        urlPatterns = {"/secure/auth/library"}
)
/**
 * Created by Mike on 2/24/17.
 */
public class MyLibraryLoaderServlet extends HttpServlet {
    private HttpSession session;
    private List<UserMovieLink> films;
    private ArrayList<Movie> rentals;
    private UserFriendDao dao = new UserFriendDao();
    private UserDao usrDao = new UserDao();
    private List<UserFriends> friends;
    private User user;
    private MovieDao movieDao = new MovieDao();
    private UserMovieDao umdao = new UserMovieDao();
    private RentalDao rentalDao = new RentalDao();


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession();

        user = (User)session.getAttribute("user");

        friends = dao.getFriendsByUser(user.getUuid());
        List<User> myFriends = new ArrayList<>();

        if (friends != null) {
            buildFriendsList(user, myFriends);
        }

        buildLibrary();

        session.setAttribute("new", null);
        session.setAttribute("mymovies", films);
        session.setAttribute("rentals", rentals);
        session.setAttribute("friends", myFriends);

        getServletContext().getRequestDispatcher("/secure/auth/mylibrary.jsp").forward(request, response);
    }

    private void buildFriendsList(User user, List<User> myFriends) {
        for (UserFriends friend : friends
                ) {
            if (friend.getFriendb() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFrienda()));
            } else if (friend.getFrienda() == user.getUuid()) {
                myFriends.add(usrDao.getUser(friend.getFriendb()));
            }
        }
    }

    private void buildLibrary() {
//        films = new ArrayList<>();
        films = umdao.getMoviesLinkByUserID(user.getUuid());
        rentals = new ArrayList<>();

        for (UserMovieLink link: films
                ) {
            Movie mve = movieDao.getMovie(link.getMovieid().getIdmovie());

            if (rentalDao.getRentalByMovieID(link) != null) {
                rentals.add(mve);
            }
        }
    }
}
