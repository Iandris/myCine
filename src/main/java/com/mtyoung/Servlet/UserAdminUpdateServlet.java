package com.mtyoung.Servlet;


import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.UserDao;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InterruptedIOException;

@WebServlet (
        name = "UpdateUser",
        urlPatterns = {"/secure/admin/updateuser"}
)

/**
 * Created by Mike on 2/23/17.
 */
public class UserAdminUpdateServlet extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        AddressDao addrDao = new AddressDao();
        Address addr = addrDao.getAddress(Integer.parseInt(request.getParameter("addrid")));

        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(Integer.parseInt(request.getParameter("state")));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        addrDao.updateAddress(addr);

        UserDao dao = new UserDao();
        User newUser = dao.getUser(Integer.parseInt(request.getParameter("uuid")));

        newUser.setFname(request.getParameter("firstname"));
        newUser.setLname(request.getParameter("lastname"));
        newUser.setUser_name(request.getParameter("user_name"));
        newUser.setCellnumber(request.getParameter("cellnumber").replace(".","").replace("-","").replace("(","").replace(")","").replace(" ",""));
        newUser.setReminderthreshold(Integer.parseInt(request.getParameter("reminder")));
        newUser.setDefaultrentalperiod(Integer.parseInt(request.getParameter("rental")));

        if (!newUser.getPassword().equals(request.getParameter("password"))) {
            newUser.setPassword(request.getParameter("password"));
        }

        dao.updateUser(newUser);

        getServletContext().getRequestDispatcher("/secure/admin/useradmin").forward(request, response);
    }
}
