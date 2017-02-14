package com.mtyoung.persistence;

import org.junit.After;
import org.junit.Test;
import com.mtyoung.entity.User;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/13/17.
 */
public class UserDAOTest {

    UserDao dao;
    User bob;
    int newUser = 0;

    @Before
    public void setup() {
        dao = new UserDao();
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

    @After
    public void cleanup() {
        if (newUser != 0) {
            dao.deleteUser(newUser);
        }
    }

    @Test
    public void getAllUsers() throws Exception {
        newUser = dao.addUser(bob);
        List<User> users = dao.getAllUsers();
        assertTrue(users.size() > 0);
    }

    @Test
    public void getUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotNull("no user returned", dao.getUser(newUser));
        assertEquals("first not entered correctly", bob.getFname(), dao.getUser(newUser).getFname().toString());
        assertEquals("last not entered correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect role returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("incorrect address returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("incorrect Email returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
    }

    @Test
    public void addUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotEquals("no user added", 0, newUser);
        assertEquals("first not entered correctly", bob.getFname(), dao.getUser(newUser).getFname().toString());
        assertEquals("last not entered correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect role returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("incorrect address returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("incorrect Email returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
    }

    @Test
    public void deleteUser() throws Exception {
        int newUser = dao.addUser(bob);

        if (newUser != 0) {
            dao.deleteUser(newUser);
        }

        assertNull("User not successfully deleted", dao.getUser(newUser));
    }

    @Test
    public void updateUser() throws Exception {
        newUser = dao.addUser(bob);
        bob.setFname("Michael");
        bob.setLname("Smith");
        bob.setRoleid(2);
        bob.setAddressid(1);
        bob.setEmail("myoung86@charter.net");
        bob.setCellnumber("9202855911");
        bob.setPassword("Connor");
        bob.setReminderthreshold(2);
        bob.setDefaultrentalperiod(5);

        dao.updateUser(bob);
        assertEquals("first not updated correctly", bob.getFname(), dao.getUser(newUser).getFname().toString());
        assertEquals("last not updated correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("updated role not returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("updated address not returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("updated Email not returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("updated cell not returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("updated reminder threshold not returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("updated rental period not returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());    }

}