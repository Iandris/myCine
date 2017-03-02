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
            getServletContext().getRequestDispatcher("/secure/auth/home.jsp").forward(request, response);
        } else {
            movieID = Integer.parseInt(request.getParameter("movieID"));
        }

        String destination = request.getParameter("destination");

        if (destination.equals("Wishlist")) {
            List<Movie> links = mvDao.getMovieListByWishlist(
                    wishlistDao.getWishListByUserID(user.getUuid()));

            //TODO after hibernate many to many rewrite lookup
            //if movie not contained within user movie link
            if (!links.contains(mvDao.getMovie(movieID))) {
                Wishlist listItem = new Wishlist();
                listItem.setMovieid(movieID);
                listItem.setUserid(user.getUuid());
                wishlistDao.addWishListItem(listItem);
            }
        } else if (destination.equals("Library")) {
            List<Movie> links = mvDao.getMovieListByLinks(
                    libraryDao.getMovieLinkByUserID(user.getUuid()));

            //TODO after hibernate many to many rewrite lookup
            //if movie not contained within user movie link
            if (!links.contains(mvDao.getMovie(movieID))) {
                UserMovieLink librarylink = new UserMovieLink();
                librarylink.setMovieid(movieID);
                librarylink.setUserid(user.getUuid());
                librarylink.setQuantity(1);
                librarylink.setStarrating(0);
                libraryDao.addUserMovie(librarylink);
            }

        } else {
            getServletContext().getRequestDispatcher("/secure/auth/mywishlist.jsp").forward(request, response);
        }



    }
}
