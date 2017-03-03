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

@WebServlet(
        name = "AddLibrary",
        urlPatterns = { "/secure/auth/addlibrary" }
)

/**
 * Created by Mike on 3/1/17.
 */
public class AddMovieLibraryServlet extends HttpServlet {
    MovieDao mvDao = new MovieDao();
    UserMovieDao libraryDao = new UserMovieDao();
    WishlistDao  wishlistDao = new WishlistDao();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int movieID = 0;

        if (request.getParameter("movieID") == null) {
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        } else {
            movieID = Integer.parseInt(request.getParameter("movieID"));
        }

        String destination = request.getParameter("destination");


        if (destination.equals("Wishlist")) {
            Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);


            if (wishlist == null) {
                wishlistDao.addWishListItem(addMovieToWishlist(mvDao.getMovie(movieID), user));
            }



            response.sendRedirect("/mycine/secure/auth/wishlist");

        } else if (destination.equals("Library")) {
            UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
            Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);

            if (library == null) {
                //movie not currently in library
                if (wishlist != null) {
                    //movie currently in wishlist
                    wishlistDao.deleteWishListItem(wishlist.getIdwishlistlink());
                }

                libraryDao.addUserMovie(addMovieToLibrary(mvDao.getMovie(movieID), user));
            }

            response.sendRedirect("/mycine/secure/auth/library");

        } else {
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        }
    }

    public Wishlist addMovieToWishlist(Movie movieID, User user) {
        Wishlist listItem = new Wishlist();
        listItem.setMovieid(movieID);
        listItem.setUserid(user);
        return listItem;
    }

    public UserMovieLink addMovieToLibrary(Movie movieID, User user) {
        UserMovieLink librarylink = new UserMovieLink();
        librarylink.setMovieid(movieID);
        librarylink.setUserid(user);
        librarylink.setQuantity(1);
        librarylink.setStarrating(0);
        return librarylink;
    }

}
