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
    Movie[] films;
    ArrayList<Movie> rentals;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        session = request.getSession();

        UserFriendDao dao = new UserFriendDao();
        UserDao usrDao = new UserDao();
        User user = (User)session.getAttribute("user");

        List<UserFriends> friends = dao.getFriendsByUser(user.getUuid());
        List<User> myFriends = new ArrayList<>();

        if (friends != null) {
            for (UserFriends friend : friends
                    ) {
                if (friend.getFrienda() == user.getUuid()) {
                    myFriends.add(usrDao.getUser(friend.getFriendb()));
                } else if (friend.getFriendb() == user.getUuid()) {
                    myFriends.add(usrDao.getUser(friend.getFrienda()));
                }
            }
        }

        buildLibrary();

        session.setAttribute("new", null);

        if(session.getAttribute("mymovies") != null) {
            session.setAttribute("mymovies", null);
        }

        //TODO populate list of my movies that are rented out

        session.setAttribute("mymovies", films);
        session.setAttribute("rentals", rentals);
        session.setAttribute("friends", myFriends);

        getServletContext().getRequestDispatcher("/secure/auth/mylibrary.jsp").forward(request, response);
    }

    public void buildLibrary() {
        User user = (User)session.getAttribute("user");
        MovieDao dao = new MovieDao();
        UserMovieDao umdao = new UserMovieDao();
        RentalDao rentalDao = new RentalDao();
        rentals = new ArrayList<Movie>();


        List<UserMovieLink> links = umdao.getMoviesLinkByUserID(user.getUuid());
        films = new Movie[links.size()];

        int i =0;
        for (UserMovieLink link: links
                ) {
            Movie mve = dao.getMovie(link.getMovieid().getIdmovie());
            films[i] = mve;

            if (rentalDao.getRentalByMovieID(link) != null) {
                rentals.add(mve);
            }

            i++;
        }

        Arrays.sort(films, Movie.MovieNameComparator);

    }
}
