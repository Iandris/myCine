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
import java.util.Arrays;
import java.util.List;

@WebServlet(
        name = "WishlistLoader",
        urlPatterns = {"/secure/auth/wishlist"}
)

/**
 * Created by Mike on 2/28/17.
 */
public class WishlistLoaderServlet extends HttpServlet {
private HttpSession session;
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
        User user = (User) session.getAttribute("user");

        Movie[] mymovies = buildLibrary();

        session.setAttribute("mymovies", mymovies);

        getServletContext().getRequestDispatcher("/secure/auth/mywishlist.jsp").forward(request, response);

    }

    private Movie[] buildLibrary() {

        User user = (User)session.getAttribute("user");

        MovieDao dao = new MovieDao();
        WishlistDao wishlistDao = new WishlistDao();

        List<Wishlist> links = wishlistDao.getWishListByUserID(user.getUuid());
        Movie[] films = new Movie[links.size()];

        int i =0;
        for (Wishlist link: links
                ) {
            films[i] = dao.getMovie(link.getMovieid().getIdmovie());
            i++;
        }

        Arrays.sort(films, Movie.MovieNameComparator);
        return films;
    }
}