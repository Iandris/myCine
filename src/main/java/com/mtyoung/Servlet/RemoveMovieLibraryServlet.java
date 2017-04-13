package com.mtyoung.Servlet;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.entity.Wishlist;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserMovieDao;
import com.mtyoung.persistence.WishlistDao;
import com.mtyoung.util.TextMessage;

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
    RentalDao rentalDao = new RentalDao();
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
            session.setAttribute("reminder", null);
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
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/" + returnURL);
            } else if (source.equals("Library")) {
                UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

                Rental rental = rentalDao.getRentalByMovieID(library);

                if (rental != null) {
                    rentalDao.deleteRental(rental.getIdrentals());
                }

                libraryDao.deleteMovieLink(library.getLinkid());


                returnURL = "secure/auth/library";
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/" + returnURL);

            } else {
                returnURL = "home";
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/" + returnURL);
            }
        } else if (destination.equals("rental")) {
            UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

            session.setAttribute("link", library);

            User renter = userdao.getUserByEmail(request.getParameter("renter"));
            session.setAttribute("renter", renter);
            session.setAttribute("return", false);
            session.setAttribute("reminder", null);
            response.sendRedirect("/mycine/secure/auth/rental");
        } else if (destination.equals("Library")) {
            session.setAttribute("reminder", null);
            getServletContext().getRequestDispatcher("/secure/auth/addlibrary").forward(request, response);
        } else if (destination.equals("returns")) {

            UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
            session.setAttribute("link", library);
            session.setAttribute("return", true);
            session.setAttribute("reminder", null);
            response.sendRedirect("/mycine/secure/auth/rental");
        } else if (destination.equals("reminder")) {
            TextMessage message = new TextMessage();
            UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

            Rental rental = rentalDao.getRentalByMovieID(library);

            if (rental != null) {
                User renter = userdao.getUserByEmail(rental.getRenterid().getUser_name());

                String body = user.getFname() + " " + user.getLname() + " would like to remind you that your rental " +
                        "of movie " + library.getMovieid().getTitle() + " is due for return.";

                String messageSID = message.sendMessate(renter.getCellnumber(), body);
                String status = "Reminder Message Failed to Send, Please try again later";

                if (messageSID != null) {
                    if (messageSID.equals("Invalid phone number for recipient") || messageSID.equals("Message Failure")) {
                        status = "Invalid phone number for recipient";
                    } else {
                        status = "Reminder Sent Successfully";
                    }
                }
                session.setAttribute("reminder", status);
            }
            returnURL = "secure/auth/library";
            response.sendRedirect("/mycine/" + returnURL);
        }
        else {
            returnURL = "home";
            session.setAttribute("reminder", null);
            response.sendRedirect("/mycine/" + returnURL);
        }
    }
}
