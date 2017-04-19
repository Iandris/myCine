package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import org.junit.After;
import org.junit.Test;
import com.mtyoung.entity.User;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/13/17.
 */
public class UserDaoTest {

    UserDao dao;
    User bob;
    Address mail;
    AddressDao mailDao;
    StateDao stateDao;
    int newMail = 0;
    int newMail2 = 0;
    int newUser = 0;

    @Before
    public void setup() {
        stateDao = new StateDao();
        //prep address table first, or user insert will fail on constraint
        mailDao = new AddressDao();
        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("nnnnn");
        mail.setCity("Watertown");
        mail.setState(stateDao.getState(49));
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);

        dao = new UserDao();
        bob = new User();
        bob.setFname("Mike");
        bob.setLname("Young");
        bob.setAddress(mail);
        bob.setUser_name("bob@email.com");
        bob.setCellnumber("0000000000");
        bob.setReminderthreshold(1);
        bob.setDefaultrentalperiod(3);
        bob.setPassword("password");

    }

    @After
    public void cleanup() {
        //cleanup temp user for tests
        if (newUser != 0) {
           dao.deleteUser(newUser);
        }

        //cleanup temp address for user insertion
        if (newMail != 0) {
            mailDao.deleteAddress(newMail);
        }

        //cleanup temp address for update user
        if (newMail2 != 0) {
            mailDao.deleteAddress(newMail2);
        }

    }

    @Test
    public void getAllUsers() throws Exception {
        newUser = dao.addUser(bob);
        List<User> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUserByEmail() throws Exception {
        newUser = dao.addUser(bob);
        assertNotNull("no user returned", dao.getUserByEmail(bob.getUser_name()));
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUserByEmail(bob.getUser_name()).getUuid());
        assertEquals("first not returned correctly", bob.getFname(), dao.getUserByEmail(bob.getUser_name()).getFname());
        assertEquals("last not returned correctly", bob.getLname(), dao.getUserByEmail(bob.getUser_name()).getLname());
        assertEquals("incorrect address returned", bob.getAddress().getIdaddresses(), dao.getUserByEmail(bob.getUser_name()).getAddress().getIdaddresses());
        assertEquals("incorrect Email returned", bob.getUser_name(), dao.getUserByEmail(bob.getUser_name()).getUser_name());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUserByEmail(bob.getUser_name()).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUserByEmail(bob.getUser_name()).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUserByEmail(bob.getUser_name()).getDefaultrentalperiod());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUserByEmail(bob.getUser_name()).getPassword());

    }

    @Test
    public void getUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotNull("no user returned", dao.getUser(newUser));
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUser(newUser).getUuid());
        assertEquals("first not returned correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not returned correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect address returned", bob.getAddress().getIdaddresses(), dao.getUser(newUser).getAddress().getIdaddresses());
        assertEquals("incorrect Email returned", bob.getUser_name(), dao.getUser(newUser).getUser_name());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUserByEmail(bob.getUser_name()).getPassword());
    }

    @Test
    public void addUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotEquals("no user added", 0, newUser);
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUser(newUser).getUuid());
        assertEquals("first not entered correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not entered correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect address returned", bob.getAddress().getIdaddresses(), dao.getUser(newUser).getAddress().getIdaddresses());
        assertEquals("incorrect Email returned", bob.getUser_name(), dao.getUser(newUser).getUser_name());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUserByEmail(bob.getUser_name()).getPassword());
    }

    @Test
    public void deleteUser() throws Exception {
        dao.addUser(bob);
        dao.deleteUser(bob.getUuid());
        assertNull("user not deleted", dao.getUser(bob.getUuid()));
    }

    @Test
    public void updateUser() throws Exception {
        Address mail2 = new Address();
        mail2.setStreetaddress1("abc 1234");
        mail2.setStreetaddress2("nnnnn");
        mail2.setCity("Madison");
        mail2.setState(stateDao.getState(42));
        mail2.setZipcode(55555);
        newMail2 = mailDao.addAddress(mail2);

        newUser = dao.addUser(bob);
        bob.setFname("Michael");
        bob.setLname("Smith");
        bob.setAddress(mail2);
        bob.setUser_name("bob2@email.com");
        bob.setCellnumber("9999999999");
        bob.setReminderthreshold(2);
        bob.setDefaultrentalperiod(5);
        bob.setPassword("password2");

        dao.updateUser(bob);
        assertEquals("first not updated correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not updated correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("updated address not returned", bob.getAddress().getIdaddresses(), dao.getUser(newUser).getAddress().getIdaddresses());
        assertEquals("updated Email not returned", bob.getUser_name(), dao.getUser(newUser).getUser_name());
        assertEquals("updated cell not returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("updated reminder threshold not returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("updated rental period not returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUserByEmail(bob.getUser_name()).getPassword());
    }

    @Test
    public void getUserByPhone() throws Exception {
        newUser = dao.addUser(bob);
        assertEquals("User not found by phone number lookup", bob.getUuid(), dao.getUserByPhone(bob.getCellnumber()).getUuid());
    }
}