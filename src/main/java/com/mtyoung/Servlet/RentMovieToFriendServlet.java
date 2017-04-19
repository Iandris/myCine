package com.mtyoung.Servlet;

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserMovieDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(
        name = "Rental",
        urlPatterns = { "/secure/auth/rental" }
)
/**
 * Created by Mike on 3/28/17.
 */
public class RentMovieToFriendServlet extends HttpServlet {
    private RentalDao rentalDao = new RentalDao();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserMovieLink link = (UserMovieLink)session.getAttribute("link");
        User user = (User) session.getAttribute("user");

        User renter = (User)session.getAttribute("renter");
        Boolean returns = (Boolean)session.getAttribute("return");

        if (rentalDao.getRentalByMovieID(link) != null) {
            rentalDao.deleteRental(rentalDao.getRentalByMovieID(link).getIdrentals());
        }

        if (!returns) {
            Rental rental = new Rental();
            rental.setMovieid(link);
            LocalDateTime duedate = LocalDateTime.now().plusDays(user.getDefaultrentalperiod());
            rental.setDuedate(duedate);
            rental.setRenterid(renter);
            rentalDao.addRental(rental);
            response.sendRedirect("/mycine/secure/auth/friends");
        } else {
            response.sendRedirect("/mycine/secure/auth/library");
        }
    }
}
