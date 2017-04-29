package com.mtyoung.Servlet.functional;

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
    private UserMovieDao libraryDao = new UserMovieDao();
    private WishlistDao wishlistDao = new WishlistDao();
    private RentalDao rentalDao = new RentalDao();
    private UserDao userdao = new UserDao();
    private User renter;
    private User user;
    private UserMovieLink library;
    private String returnURL;

    /**
     * doPost method for RemoveLibrary
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");

        String source = request.getParameter("source");
        String destination = request.getParameter("destination");

        if (request.getParameter("movieID") == null) {
            session.setAttribute("reminder", null);
            response.sendRedirect("/mycine/secure/auth/moviesearch");
        }

        int movieID = Integer.parseInt(request.getParameter("movieID"));

        switch (destination) {
            case "trash":
                switch (source) {
                    case "Wishlist" :
                        deleteFromWishlist(movieID);
                        break;
                    case "Library" :
                        deleteFromLibrary(movieID);
                        break;
                    default:
                        returnURL = "home";
                        break;
                }
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/" + returnURL);
                break;
            case "rental":
                createRental(request, session, movieID);
                response.sendRedirect("/mycine/secure/auth/rental");
                break;
            case "Library" :
                session.setAttribute("reminder", null);
                getServletContext().getRequestDispatcher("/secure/auth/addlibrary").forward(request, response);
                break;
            case "returns" :
                returnRental(session, movieID);
                response.sendRedirect("/mycine/secure/auth/rental");
                break;
            case "reminder" :
                TextMessage message = new TextMessage();
                library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

                Rental rental = rentalDao.getRentalByMovieID(library);

                if (rental != null) {
                    sendTextReminder(session, message, rental);
                }
                returnURL = "secure/auth/library";
                response.sendRedirect("/mycine/" + returnURL);
                break;
            default:
                returnURL = "home";
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/" + returnURL);
                break;
        }
    }

    /**
     * sendTextReminder method, calls TextMessage class to build and send a text reminder to renter of selected title
     * @param session
     * @param message
     * @param rental
     */
    private void sendTextReminder(HttpSession session, TextMessage message, Rental rental) {
        renter = userdao.getUserByEmail(rental.getRenterid().getUser_name());

        String body = user.getFname() + " " + user.getLname() + " would like to remind you that your rental " +
                "of movie " + library.getMovieid().getTitle() + " is due for return.";

        String messageSID = message.sendMessage(renter.getCellnumber(), body);
        String status = "Reminder Message Failed to Send, Please try again later";

        if (messageSID != null) {
            status = getString(messageSID);
        }
        session.setAttribute("reminder", status);
    }

    /**
     * returnRental method - cancels the active rental for user
     * @param session
     * @param movieID
     */
    private void returnRental(HttpSession session, int movieID) {
        library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
        session.setAttribute("link", library);
        session.setAttribute("return", true);
        session.setAttribute("reminder", null);
    }

    /**
     * createRental method, adds a new rental for current user with selected title
     * @param request
     * @param session
     * @param movieID
     */
    private void createRental(HttpServletRequest request, HttpSession session, int movieID) {
        library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
        renter = userdao.getUserByEmail(request.getParameter("renter"));

        session.setAttribute("link", library);
        session.setAttribute("renter", renter);
        session.setAttribute("return", false);
        session.setAttribute("reminder", null);
    }

    /**
     * deleteFromWishlist method - for selected title, if exists in wishlist, removes from list
     * @param movieID
     */
    private void deleteFromWishlist(int movieID) {
        Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);

        returnURL = "secure/auth/wishlist";
        if (wishlist != null) {
            wishlistDao.deleteWishListItem(wishlist.getIdwishlistlink());
        }
    }

    /**
     * deleteFromLibrary method - for selected title, if exists in library, removes from list
     * @param movieID
     */
    private void deleteFromLibrary(int movieID) {
        UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

        Rental rental = rentalDao.getRentalByMovieID(library);

        if (rental != null) {
            rentalDao.deleteRental(rental.getIdrentals());
        }

        libraryDao.deleteMovieLink(library.getLinkid());

        returnURL = "secure/auth/library";
    }

    /**
     * returns formatted string success message from text message
     * @param messageSID
     * @return
     */
    private String getString(String messageSID) {
        String status;
        if (messageSID.equals("Invalid phone number for recipient") || messageSID.equals("Message Failure")) {
            status = "Invalid phone number for recipient";
        } else {
            status = "Reminder Sent Successfully";
        }
        return status;
    }
}
