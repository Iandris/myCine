package com.mtyoung.persistence;

import org.hibernate.Session;
import org.junit.Test;
import com.mtyoung.entity.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/13/17.
 */
public class UserDAOTest {

    UserDAO dao;
    User bob;

    @Before
    public void setup() {
        dao = new UserDAO();
        bob = new User();
        bob.setFname("Mike");
        bob.setLname("Young");
        bob.setRoleid(1);
        bob.setAddressid(2);
        bob.setEmail("mtyoung@madisoncollege.edu");
        bob.setCellnumber("6083334717");
        bob.setPassword("Password");
        bob.setReminderthreshold(1);
        bob.setDefaultrentalperiod(3);
    }

    @Test
    public void getAllUsers() throws Exception {
        List<User> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void addUser() throws Exception {
        assertNotEquals("no user added", 0, dao.addUser(bob));
    }

    @Test
    public void deleteUser() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

}