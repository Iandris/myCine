package com.mtyoung.util;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.AddressDao;
import com.mtyoung.persistence.StateDao;
import com.mtyoung.persistence.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mike on 3/6/17.
 */
public class EmailMessageTest {
    EmailMessage email;
    UserDao userDao;
    User bob1;
    User bob2;

    Address mail;
    AddressDao mailDao;
    StateDao stateDao;

    int newMail = 0;
    int newUser1 = 0;
    int newUser2 = 0;

    @Before
    public void setUp() throws Exception {
        stateDao = new StateDao();
        mailDao = new AddressDao();
        userDao = new UserDao();

        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("");
        mail.setCity("Watertown");
        mail.setState(stateDao.getState(49));
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);

        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setAddress(mail);
        bob1.setUser_name("mtyoung@madisoncollege.edu");
        bob1.setCellnumber("4444444444");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        bob1.setPassword("Password");
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("John");
        bob2.setLname("Smith");
        bob2.setAddress(mail);
        bob2.setUser_name("mike.young@charter.com");
        bob2.setCellnumber("3333333333");
        bob2.setReminderthreshold(1555);
        bob2.setDefaultrentalperiod(39);
        bob2.setPassword("Password");
        newUser2 = userDao.addUser(bob2);

        email = new EmailMessage();
    }

    @After
    public void tearDown() throws Exception {
        if (email != null) {
            email = null;
        }

        if (newUser2 != 0) {
            userDao.deleteUser(newUser2);
        }

        if (newUser1 != 0) {
            userDao.deleteUser(newUser1);
        }

        if (newMail != 0) {
            mailDao.deleteAddress(newMail);
        }
    }

    @Test
    public void sendFriendRequest() throws Exception {
        assertEquals("message not successfully sent", true, email.sendFriendRequest(bob1, bob2));
    }

}