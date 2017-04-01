package com.mtyoung.Servlet;

import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.persistence.RentalDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "Rental",
        urlPatterns = { "/secure/auth/rental" }
)
/**
 * Created by Mike on 3/28/17.
 */
public class RentMovieToFriendServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RentalDao rentalDao = new RentalDao();

        HttpSession session = request.getSession();
        UserMovieLink link = (UserMovieLink)session.getAttribute("link");




    }
}
