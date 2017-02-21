package com.mtyoung.Servlet;


import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "Enroll",
        urlPatterns = { "/enroll" }
)
/**
 * Created by Mike on 2/16/17.
 */
public class EnrollServlet  extends HttpServlet {
    /**
     * doGet method for mycine Enrollredirect
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        AddressDao addrDao = new AddressDao();
        Address addr = new Address();

        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(Integer.parseInt(request.getParameter("state")));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        int newaddr = addrDao.addAddress(addr);
        UserDao dao = new UserDao();
        User newUser = new User();

        newUser.setFname(request.getParameter("firstname"));
        newUser.setLname(request.getParameter("lastname"));
        newUser.setAddressid(newaddr);
        newUser.setUser_name(request.getParameter("email"));
        newUser.setCellnumber(request.getParameter("cellnumber").replace(".","").replace("-","").replace("(","").replace(")","").replace(" ",""));
        newUser.setReminderthreshold(1);
        newUser.setDefaultrentalperiod(3);
        newUser.setPassword(request.getParameter("password"));

        int userID =  dao.addUser(newUser);

        if (userID != 0) {
            //getServletContext().getRequestDispatcher("/home").forward(request, response);
            response.sendRedirect("/mycine/secure/home");
        } else {
            //getServletContext().getRequestDispatcher("/index").forward(request, response);
            response.sendRedirect("/mycine/index");
        }

    }
}