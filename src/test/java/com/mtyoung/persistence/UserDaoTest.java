package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.UserRole;
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
    UserRole role;
    UserRoleDao roleDao;
    int newRole = 0;
    int newMail = 0;
    int newMail2 = 0;
    int newUser = 0;

    @Before
    public void setup() {

        //prep address table first, or user insert will fail on constraint
        mailDao = new AddressDao();
        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("nnnnn");
        mail.setCity("Watertown");
        mail.setState(49);
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);

        role = new UserRole();
        roleDao = new UserRoleDao();
        role.setDescription("CHIPS n SALSA");
        newRole = roleDao.addRole(role);

        dao = new UserDao();
        bob = new User();
        bob.setFname("Mike");
        bob.setLname("Young");
        bob.setRoleid(newRole);
        bob.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob.setEmail("mtyoung@madisoncollege.edu");
        bob.setCellnumber("6083334717");
        bob.setPassword("Password");
        bob.setReminderthreshold(1);
        bob.setDefaultrentalperiod(3);
        bob.setFirebaseUID("temporary string");
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

        if (newRole != 0) {
            roleDao.deleteRole(newRole);
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
        assertNotNull("no user returned", dao.getUserByEmail(bob.getEmail()));
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUserByEmail(bob.getEmail()).getUuid());
        assertEquals("first not returned correctly", bob.getFname(), dao.getUserByEmail(bob.getEmail()).getFname());
        assertEquals("last not returned correctly", bob.getLname(), dao.getUserByEmail(bob.getEmail()).getLname());
        assertEquals("incorrect role returned", bob.getRoleid(), dao.getUserByEmail(bob.getEmail()).getRoleid());
        assertEquals("incorrect address returned", bob.getAddressid(), dao.getUserByEmail(bob.getEmail()).getAddressid());
        assertEquals("incorrect Email returned", bob.getEmail(), dao.getUserByEmail(bob.getEmail()).getEmail());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUserByEmail(bob.getEmail()).getPassword());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUserByEmail(bob.getEmail()).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUserByEmail(bob.getEmail()).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUserByEmail(bob.getEmail()).getDefaultrentalperiod());

    }

    @Test
    public void getUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotNull("no user returned", dao.getUser(newUser));
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUser(newUser).getUuid());
        assertEquals("first not returned correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not returned correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect role returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("incorrect address returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("incorrect Email returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUser(newUser).getPassword());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
    }

    @Test
    public void addUser() throws Exception {
        newUser = dao.addUser(bob);
        assertNotEquals("no user added", 0, newUser);
        assertEquals("user ID not returned correctly", bob.getUuid(), dao.getUser(newUser).getUuid());
        assertEquals("first not entered correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not entered correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("incorrect role returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("incorrect address returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("incorrect Email returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUser(newUser).getPassword());
        assertEquals("incorrect cell returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("incorrect reminder threshold returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("incorrect rental period returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
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
        mail2.setState(42);
        mail2.setZipcode(55555);
        newMail2 = mailDao.addAddress(mail2);

        newUser = dao.addUser(bob);
        bob.setFname("Michael");
        bob.setLname("Smith");
        bob.setRoleid(newRole);
        bob.setAddressid(mailDao.getAddress(newMail2).getIdaddresses());
        bob.setEmail("myoung86@charter.net");
        bob.setCellnumber("9202855911");
        bob.setPassword("Connor");
        bob.setReminderthreshold(2);
        bob.setDefaultrentalperiod(5);

        dao.updateUser(bob);
        assertEquals("first not updated correctly", bob.getFname(), dao.getUser(newUser).getFname());
        assertEquals("last not updated correctly", bob.getLname(), dao.getUser(newUser).getLname());
        assertEquals("updated role not returned", bob.getRoleid(), dao.getUser(newUser).getRoleid());
        assertEquals("updated address not returned", bob.getAddressid(), dao.getUser(newUser).getAddressid());
        assertEquals("updated Email not returned", bob.getEmail(), dao.getUser(newUser).getEmail());
        assertEquals("incorrect password returned", bob.getPassword(), dao.getUser(newUser).getPassword());
        assertEquals("updated cell not returned", bob.getCellnumber(), dao.getUser(newUser).getCellnumber());
        assertEquals("updated reminder threshold not returned", bob.getReminderthreshold(), dao.getUser(newUser).getReminderthreshold());
        assertEquals("updated rental period not returned", bob.getDefaultrentalperiod(), dao.getUser(newUser).getDefaultrentalperiod());
    }


}