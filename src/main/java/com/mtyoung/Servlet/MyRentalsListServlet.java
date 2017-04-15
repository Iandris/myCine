package com.mtyoung.Servlet;

/**
 * Created by Mike on 4/13/17.
 */

import com.mtyoung.entity.Rental;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import com.mtyoung.entity.UserMovieLink;
import com.mtyoung.persistence.RentalDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserFriendDao;
import com.mtyoung.persistence.UserMovieDao;

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
        name = "MyRentals",
        urlPatterns = {"/secure/auth/rentals"}
        )
public class MyRentalsListServlet  extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        RentalDao dao = new RentalDao();

        List<Rental> myRentals = dao.getRentalsByRenter(user);

        session.setAttribute("myrentals", myRentals);
        getServletContext().getRequestDispatcher("/secure/auth/myrentals.jsp").forward(request, response);
    }
}
