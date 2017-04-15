package com.mtyoung.Servlet;


import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserRole;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserRoleDao;
import org.apache.catalina.realm.RealmBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        HttpSession session = request.getSession();
        AddressDao addrDao = new AddressDao();
        Address addr = new Address();
        StateDao stateDao = new StateDao();


        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(stateDao.getState(Integer.parseInt(request.getParameter("state"))));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        int newaddr = addrDao.addAddress(addr);

        UserDao dao = new UserDao();
        User newUser = new User();

        newUser.setFname(request.getParameter("firstname"));
        newUser.setLname(request.getParameter("lastname"));
        newUser.setAddress(addr);
        newUser.setUser_name(request.getParameter("user_name"));
        newUser.setCellnumber(request.getParameter("cellnumber").replace(".","").replace("-","").replace("(","").replace(")","").replace(" ",""));
        newUser.setReminderthreshold(1);
        newUser.setDefaultrentalperiod(3);
        newUser.setPassword(RealmBase.Digest(request.getParameter("password"),"sha-256", "UTF-8"));

        int userID =  dao.addUser(newUser);


        if (userID != 0) {

            UserRoleDao roleDao = new UserRoleDao();
            UserRole role = new UserRole();
            role.setRole_name("registered-user");
            role.setuser_name(newUser.getUser_name());
            int i = roleDao.addRole(role);

            if (i != 0) {
                request.login(newUser.getUser_name(), request.getParameter("password"));
                session.setAttribute("user", dao.getUser(userID));

//                String url = "j_security_check?j_username=" + newUser.getUser_name() + "&j_password=" + request.getParameter("password");
//                String redirectURL = response.encodeRedirectURL(url);
//                response.sendRedirect(redirectURL);

                response.sendRedirect("/mycine/secure/auth/home");
            } else {
                response.sendRedirect("/mycine/index");
            }
        } else {
            //getServletContext().getRequestDispatcher("/index").forward(request, response);
            response.sendRedirect("/mycine/index");
        }

    }
}