package com.mtyoung.Servlet;

import com.mtyoung.entity.Movie;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.entity.Wishlist;
import com.mtyoung.persistence.MovieDao;
import com.mtyoung.persistence.UserMovieDao;
import com.mtyoung.persistence.WishlistDao;

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
        name = "AddLibrary",
        urlPatterns = { "/secure/auth/addlibrary" }
)

/**
 * Created by Mike on 3/1/17.
 */
public class AddMovieLibraryServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        MovieDao mvDao = new MovieDao();
        UserMovieDao libraryDao = new UserMovieDao();
        WishlistDao  wishlistDao = new WishlistDao();
        int movieID = 0;

        if (request.getParameter("movieID") == null) {
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        } else {
            movieID = Integer.parseInt(request.getParameter("movieID"));
        }

        System.out.println(movieID);

        String destination = request.getParameter("destination");


        if (destination.equals("Wishlist")) {
            List<Movie> movies = mvDao.getMovieListByWishlist(
                    wishlistDao.getWishListByUserID(user.getUuid()));

            Movie mo = mvDao.getMovie(movieID);

            if (movies != null) {
                //TODO after hibernate many to many rewrite lookup
                //if movie not contained within user movie link
                if (!movies.contains(mo)) {
                    wishlistDao.addWishListItem(addMovieToWishlist(movieID, user));
                }
            } else {
                wishlistDao.addWishListItem(addMovieToWishlist(movieID, user));
            }

            response.sendRedirect("/mycine/secure/auth/wishlist");
           //getServletContext().getRequestDispatcher("/secure/auth/wishlist").forward(request, response);

        } else if (destination.equals("Library")) {
           // List<Movie> links = mvDao.getMovieListByLinks(
             //       libraryDao.getMovieLinkByUserID(user.getUuid()));

            List<UserMovieLink> links = libraryDao.getMovieLinkByUserID(user.getUuid());
            List<Integer> movieIDs = null;

            for (UserMovieLink link: links
                 ) {
                movieIDs.add(link.getMovieid());
            }

            if (!movieIDs.contains(movieID)) {
                libraryDao.addUserMovie(addMovieToLibrary(movieID, user));
            }
//
//            //TODO after hibernate many to many rewrite lookup
//            //if movie not contained within user movie link
//            if (links != null) {
//                if (!links.contains(mvDao.getMovie(movieID))) {
//                    libraryDao.addUserMovie(addMovieToLibrary(movieID, user));
//                }
//            } else {
//                //not handling the event that the movie list is null due to lookup error, duplicate movies being added
//                libraryDao.addUserMovie(addMovieToLibrary(movieID, user));
//            }
            response.sendRedirect("/mycine/secure/auth/library");
           // getServletContext().getRequestDispatcher("/secure/auth/library").forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        }
    }

    public Wishlist addMovieToWishlist(int movieID, User user) {
        Wishlist listItem = new Wishlist();
        listItem.setMovieid(movieID);
        listItem.setUserid(user.getUuid());
        return listItem;
    }

    public UserMovieLink addMovieToLibrary(int movieID, User user) {
        UserMovieLink librarylink = new UserMovieLink();
        librarylink.setMovieid(movieID);
        librarylink.setUserid(user.getUuid());
        librarylink.setQuantity(1);
        librarylink.setStarrating(0);
        return librarylink;
    }
}
