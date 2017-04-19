package com.mtyoung.util;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserRole;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import com.mtyoung.persistence.UserRoleDao;
import org.apache.catalina.realm.RealmBase;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Mike on 4/19/17.
 */
public class UserUpdater {
    private AddressDao addrDao = new AddressDao();
    private StateDao stateDao = new StateDao();
    private UserDao dao = new UserDao();
    private UserRoleDao roleDao = new UserRoleDao();

    public boolean createUser(HttpServletRequest request, User newUser, Address addr) throws IOException {
        boolean success = true;
        newUser.setFname(request.getParameter("firstname"));
        newUser.setLname(request.getParameter("lastname"));
        newUser.setAddress(addr);
        newUser.setCellnumber(request.getParameter("cellnumber").replace(".","").replace("-","").replace("(","").replace(")","").replace(" ",""));
        newUser.setUser_name(request.getParameter("user_name"));
        newUser.setReminderthreshold(1);
        newUser.setDefaultrentalperiod(3);
        newUser.setPassword(RealmBase.Digest(request.getParameter("password"),"sha-256", "UTF-8"));

        if (dao.addUser(newUser) == 0) {
            success = false;
        }
        return success;
    }

    public Address createAddress(HttpServletRequest request) {
        Address addr = new Address();
        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(stateDao.getState(Integer.parseInt(request.getParameter("state"))));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        addrDao.addAddress(addr);
        return addr;
    }

    public void createRole(User newUser) {
        UserRole role = new UserRole();
        role.setRole_name("registered-user");
        role.setuser_name(newUser.getUser_name());
        roleDao.addRole(role);
    }

    public void updateUser(HttpServletRequest request, User user) {
        user.setFname(request.getParameter("firstname"));
        user.setLname(request.getParameter("lastname"));
        user.setUser_name(request.getParameter("user_name"));
        user.setCellnumber(request.getParameter("cellnumber").replace(".", "").replace("-", "").replace("(", "").replace(")", "").replace(" ", ""));
        user.setReminderthreshold(Integer.parseInt(request.getParameter("reminder")));
        user.setDefaultrentalperiod(Integer.parseInt(request.getParameter("rental")));

        if (!user.getPassword().equals(request.getParameter("password"))) {
            user.setPassword(RealmBase.Digest(request.getParameter("password"), "sha-256", "UTF-8"));
        }

        dao.updateUser(user);
    }

    public void updateAddress(HttpServletRequest request) {
        Address addr = addrDao.getAddress(Integer.parseInt(request.getParameter("addrid")));
        addr.setStreetaddress1(request.getParameter("address1"));
        addr.setStreetaddress2(request.getParameter("address2"));
        addr.setCity(request.getParameter("city"));
        addr.setState(stateDao.getState(Integer.parseInt(request.getParameter("state"))));
        addr.setZipcode(Integer.parseInt(request.getParameter("zip")));
        addrDao.updateAddress(addr);
    }

    public void updateUserRole(User newUser, String username) {
        UserRole role = roleDao.getRoleByUserName(username);

        if (role != null) {
            role.setuser_name(newUser.getUser_name());
            roleDao.updateRole(role);
        }
    }
}
