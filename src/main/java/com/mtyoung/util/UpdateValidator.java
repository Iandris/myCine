package com.mtyoung.util;

import com.mtyoung.entity.User;
import com.mtyoung.persistence.UserDao;

import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mike on 4/19/17.
 */
public class UpdateValidator {
    private UserDao dao = new UserDao();

    public boolean checkEmail(String email, HttpSession session) throws IOException {
        User test = dao.getUserByEmail(email);
        Boolean success = true;
        if (test != null) {
            session.setAttribute("updateStatus", "Duplicate Email Found, please try a different username");
            success = false;
        } else {
            session.setAttribute("updateStatus", null);
        }
        return success;
    }

    public boolean checkCell(String cell, HttpSession session) throws IOException {
        cell = cell.replace(".","").replace("-","").replace("(","").replace(")","").replace(" ","");

        User test = dao.getUserByPhone(cell);
        Boolean success = true;
        if (test != null) {
            session.setAttribute("updateStatus", "Duplicate Phone Number Found, please try a different number");
            success = false;
        } else {
            session.setAttribute("updateStatus", null);
        }
        return success;
    }
}
