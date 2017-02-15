package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.User;
import com.mtyoung.entity.UserFriends;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class UserFriendDaoTest {

    UserFriendDao dao;
    UserFriends friend;

    Address mail;
    AddressDao mailDao;

    UserDao userDao;
    User bob1;
    User bob2;
    User bob3;

    int newUser1 = 0;
    int newUser2 = 0;
    int newUser3 = 0;
    int newMail = 0;
    int newFriend = 0;

    @Before
    public void setup() {
        dao = new UserFriendDao();
        friend = new UserFriends();
        mailDao = new AddressDao();
        userDao = new UserDao();

        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("");
        mail.setCity("Watertown");
        mail.setState(49);
        mail.setZipcode(53098);
        newMail = mailDao.addAddress(mail);

        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setRoleid(1);
        bob1.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob1.setEmail("mtyoung@madisoncollege.edu");
        bob1.setCellnumber("6083334717");
        bob1.setPassword("Password");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("John");
        bob2.setLname("Smith");
        bob2.setRoleid(1);
        bob2.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob2.setEmail("mail@gmail.com");
        bob2.setCellnumber("123456789");
        bob2.setPassword("Password2");
        bob2.setReminderthreshold(1555);
        bob2.setDefaultrentalperiod(39);
        newUser2 = userDao.addUser(bob2);

        bob3 = new User();
        bob3.setFname("Dave");
        bob3.setLname("Navarro");
        bob3.setRoleid(1);
        bob3.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob3.setEmail("mail@yahoo.com");
        bob3.setCellnumber("987654321");
        bob3.setPassword("Password3");
        bob3.setReminderthreshold(51123);
        bob3.setDefaultrentalperiod(123123);
        newUser3 = userDao.addUser(bob3);

        friend.setFriendidA(bob1.getUuid());
        friend.setFriendidB(bob2.getUuid());

    }

    @After
    public void cleanup() {

        if (newFriend != 0) {
            dao.deleteFriend(newFriend);
        }

        if (newUser3 != 0) {
            userDao.deleteUser(newUser3);
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
    public void getAllFriends() throws Exception {
        newFriend = dao.addFriend(friend);
        List<UserFriends> friendsList = dao.getAllFriends();
        assertTrue(friendsList.size() > 0);
    }

    @Test
    public void getFriend() throws Exception {
        newFriend = dao.addFriend(friend);
        assertTrue(newFriend != 0);
        assertEquals("friend a not returned" , friend.getFriendidA(), dao.getFriend(newFriend).getFriendidA());
        assertEquals("friend b not returned" , friend.getFriendidB(), dao.getFriend(newFriend).getFriendidB());
    }

    @Test
    public void addFriend() throws Exception {
        newFriend = dao.addFriend(friend);
        assertTrue(newFriend != 0);
        assertEquals("friend a not inserted" , friend.getFriendidA(), dao.getFriend(newFriend).getFriendidA());
        assertEquals("friend b not inserted" , friend.getFriendidB(), dao.getFriend(newFriend).getFriendidB());
    }

    @Test
    public void deleteFriend() throws Exception {
        dao.addFriend(friend);
        dao.deleteFriend(friend.getIdConnector());
        assertNull("friend not deleted", dao.getFriend(friend.getIdConnector()));
    }

    @Test
    public void updateFriend() throws Exception {
        newFriend = dao.addFriend(friend);
        assertTrue(newFriend != 0);

        friend.setFriendidB(bob3.getUuid());
        friend.setFriendidA(bob2.getUuid());
        dao.updateFriend(friend);

        assertEquals("friend a not updated", friend.getFriendidB(), dao.getFriend(friend.getIdConnector()).getFriendidB());
        assertEquals("friend a not updated", friend.getFriendidA(), dao.getFriend(friend.getIdConnector()).getFriendidA());
    }

}