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
    private UserMovieDao libraryDao = new UserMovieDao();
    private WishlistDao wishlistDao = new WishlistDao();
    private RentalDao rentalDao = new RentalDao();
    private UserDao userdao = new UserDao();
    private User renter;
    private User user;
    private UserMovieLink library;
    private String returnURL;

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
                        Wishlist wishlist = wishlistDao.getLinkByUserMovie(user.getUuid(), movieID);

                        returnURL = "secure/auth/wishlist";
                        if (wishlist != null) {
                            wishlistDao.deleteWishListItem(wishlist.getIdwishlistlink());
                        }
                        session.setAttribute("reminder", null);
                        response.sendRedirect("/mycine/" + returnURL);
                        break;
                    case "Library" :
                        UserMovieLink library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

                        Rental rental = rentalDao.getRentalByMovieID(library);

                        if (rental != null) {
                            rentalDao.deleteRental(rental.getIdrentals());
                        }

                        libraryDao.deleteMovieLink(library.getLinkid());

                        returnURL = "secure/auth/library";
                        session.setAttribute("reminder", null);
                        response.sendRedirect("/mycine/" + returnURL);
                        break;
                    default:
                        returnURL = "home";
                        session.setAttribute("reminder", null);
                        response.sendRedirect("/mycine/" + returnURL);
                        break;
                }
                break;
            case "rental":
                library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
                renter = userdao.getUserByEmail(request.getParameter("renter"));

                session.setAttribute("link", library);
                session.setAttribute("renter", renter);
                session.setAttribute("return", false);
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/secure/auth/rental");
                break;
            case "Library" :
                session.setAttribute("reminder", null);
                getServletContext().getRequestDispatcher("/secure/auth/addlibrary").forward(request, response);
                break;
            case "returns" :
                library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);
                session.setAttribute("link", library);
                session.setAttribute("return", true);
                session.setAttribute("reminder", null);
                response.sendRedirect("/mycine/secure/auth/rental");
                break;
            case "reminder" :
                TextMessage message = new TextMessage();
                library = libraryDao.getLinkByUserMovie(user.getUuid(), movieID);

                Rental rental = rentalDao.getRentalByMovieID(library);

                if (rental != null) {
                    renter = userdao.getUserByEmail(rental.getRenterid().getUser_name());

                    String body = user.getFname() + " " + user.getLname() + " would like to remind you that your rental " +
                            "of movie " + library.getMovieid().getTitle() + " is due for return.";

                    String messageSID = message.sendMessate(renter.getCellnumber(), body);
                    String status = "Reminder Message Failed to Send, Please try again later";

                    if (messageSID != null) {
                        status = getString(messageSID);
                    }
                    session.setAttribute("reminder", status);
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
