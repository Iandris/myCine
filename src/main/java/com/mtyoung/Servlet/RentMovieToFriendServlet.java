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
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RentalDao rentalDao = new RentalDao();

        HttpSession session = request.getSession();
        UserMovieLink link = (UserMovieLink)session.getAttribute("link");
        User user = (User) session.getAttribute("user");

        User renter = (User)session.getAttribute("renter");

//TODO prevent same UserMovie from being rented out twice at the same time

        if (rentalDao.getRentalByMovieID(link) != null) {
            //TODO prevent same UserMovie from being rented out twice at the same time
            //attempt to create delete event with null entity current error message
            rentalDao.deleteRental(link.getLinkid());
        }

        Rental rental = new Rental();
        rental.setMovieid(link);
        LocalDateTime duedate = LocalDateTime.now().plusDays(user.getDefaultrentalperiod());
        rental.setDuedate(duedate);
        rental.setRenterid(renter);
        rentalDao.addRental(rental);


        getServletContext().getRequestDispatcher("/secure/auth/friends").forward(request, response);

    }
}
