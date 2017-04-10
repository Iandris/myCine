package com.mtyoung.Servlet;

import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.entity.Wishlist;
import com.mtyoung.persistence.UserDao;
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
        name = "RemoveLibrary",
        urlPatterns = { "/secure/auth/removelibrary" }
)
/**
 * Created by Mike on 3/3/17.
 */
public class RemoveMovieLibraryServlet extends HttpServlet{
    UserMovieDao libraryDao = new UserMovieDao();
    WishlistDao wishlistDao = new WishlistDao();
    UserDao userdao = new UserDao();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        int movieID;
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        String returnURL;

        if (request.getParameter("movieID") == null) {
            response.sendRedirect("/mycine/secure/auth/moviesearch");
        }

        movieID = Integer.parseInt(request.getParameter("movieID"));

        if (destination.equals("trash")) {
            if (source.equals("Wishlist")) {
                Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);

                returnURL = "secure/auth/wishlist";
                if (wishlist != null) {
                    wishlistDao.deleteWishListItem(wishlist.getIdwishlistlink());
                }
                response.sendRedirect("/mycine/" + returnURL);
            } else if (source.equals("Library")) {
                UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

                libraryDao.deleteMovieLink(library.getLinkid());

                returnURL = "secure/auth/library";
                response.sendRedirect("/mycine/" + returnURL);
            } else {
                returnURL = "home";
                response.sendRedirect("/mycine/" + returnURL);
            }
        } else if (destination.equals("rental")) {
            UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

            session.setAttribute("link", library);

            User renter = (User)userdao.getUserByEmail(request.getParameter("renter"));
            System.out.println(renter.getFname());
            session.setAttribute("renter", renter);

            //TODO redirect to rental servlet
            response.sendRedirect("/mycine/secure/auth/rental");
        } else if (destination.equals("Library")) {
            getServletContext().getRequestDispatcher("/secure/auth/addlibrary").forward(request, response);
        }
        else {
            returnURL = "home";
            response.sendRedirect("/mycine/" + returnURL);
        }
    }
}
