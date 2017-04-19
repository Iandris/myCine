package com.mtyoung.Servlet.functional;

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
    private MovieDao mvDao = new MovieDao();
    private UserMovieDao libraryDao = new UserMovieDao();
    private WishlistDao  wishlistDao = new WishlistDao();
    private int movieID;
    private User user;

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        session.setAttribute("addFail", null);

        if (request.getParameter("movieID") != null) {
            movieID = Integer.parseInt(request.getParameter("movieID"));

            String destination = request.getParameter("destination");

            switch (destination) {
                case "Wishlist":
                    Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);
                    if (wishlist == null) {
                        checkLibrary(request, response, session, user);
                    } else {
                        session.setAttribute("addFail", "ERROR: Title " + mvDao.getMovie(movieID).getTitle() + " is already in Wishlist.");
                        getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
                    }
                    break;
                case "Library":
                    sendToLibrary();
                    response.sendRedirect("/mycine/secure/auth/library");
                    break;
                default:
                    getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
                    break;
            }
        } else {
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        }
    }

    private void checkLibrary(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user) throws ServletException, IOException {
        UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
        if (library != null) {
            session.setAttribute("addFail", "ERROR: Title " + mvDao.getMovie(movieID).getTitle() + " is already in Library.");
            getServletContext().getRequestDispatcher("/secure/auth/moviesearch").forward(request, response);
        } else {
            wishlistDao.addWishListItem(createWishlistLink(mvDao.getMovie(movieID), user));
            response.sendRedirect("/mycine/secure/auth/wishlist");
        }
    }

    private void sendToLibrary() {
        UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
        Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);

        if (library == null && wishlist != null) {
            //move from wishlist to library
            wishlistDao.deleteWishListItem(wishlist.getIdwishlistlink());
        }

        libraryDao.addUserMovie(createLibraryLink(mvDao.getMovie(movieID), user));
    }

    public Wishlist createWishlistLink(Movie movieID, User user) {
        Wishlist listItem = new Wishlist();
        listItem.setMovieid(movieID);
        listItem.setUserid(user);
        return listItem;
    }

    public UserMovieLink createLibraryLink(Movie movieID, User user) {
        UserMovieLink librarylink = new UserMovieLink();
        librarylink.setMovieid(movieID);
        librarylink.setUserid(user);
        librarylink.setQuantity(1);
        librarylink.setStarrating(0);
        return librarylink;
    }

}
