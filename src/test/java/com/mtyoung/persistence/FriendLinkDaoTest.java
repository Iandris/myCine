package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import com.mtyoung.entity.FriendLink;
import com.mtyoung.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class FriendLinkDaoTest {

    FriendLink link;
    FriendLinkDao dao;
    UserDao userDao;
    AddressDao mailDao;
    Address mail;

    User bob1;
    User bob2;
    User bob3;

    int newMail = 0;
    int newUser1 = 0;
    int newUser2 = 0;
    int newUser3 = 0;
    int newLink = 0;

    @Before
    public void setup() {
        link = new FriendLink();
        dao = new FriendLinkDao();
        userDao = new UserDao();
        mailDao = new AddressDao();

        mail = new Address();
        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("nnnnn");
        mail.setCity("Watertown");
        mail.setState(49);
        mail.setZipcode(53098);
        //newMail = mailDao.addAddress(mail);

        bob1 = new User();
        bob1.setFname("Mike");
        bob1.setLname("Young");
        bob1.setRoleid(1);
        //bob1.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob1.setAddressid(1);
        bob1.setEmail("mtyoung@madisoncollege.edu");
        bob1.setCellnumber("6083334717");
        bob1.setPassword("Password");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        //newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("Dave");
        bob2.setLname("Navarro");
        bob2.setRoleid(1);
        //bob2.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob2.setAddressid(1);
        bob2.setEmail("email@charter.com");
        bob2.setCellnumber("123456789");
        bob2.setPassword("Password222");
        bob2.setReminderthreshold(99);
        bob2.setDefaultrentalperiod(5);
        //newUser2 = userDao.addUser(bob2);

        bob3 = new User();
        bob3.setFname("John");
        bob3.setLname("Smith");
        bob3.setRoleid(1);
        //bob3.setAddressid(mailDao.getAddress(newMail).getIdaddresses());
        bob3.setAddressid(1);
        bob3.setEmail("email@google.com");
        bob3.setCellnumber("987654321");
        bob3.setPassword("Password3");
        bob3.setReminderthreshold(5);
        bob3.setDefaultrentalperiod(87);
        //newUser3 = userDao.addUser(bob3);

        //link.setFriendid1(bob1.getUuid());
        //link.setFriendid2(bob2.getUuid());
    }

    @After
    public void cleanup() {

        if (newLink != 0) {
            //dao.deleteFriendLink(newLink);
        }

        //cleanup temp users for insert/update
        if (newUser3 != 0) {
            //userDao.deleteUser(newUser3);
        }

        if (newUser2 != 0) {
           // userDao.deleteUser(newUser2);
        }

        if (newUser1 != 0) {
            //userDao.deleteUser(newUser1);
        }

        //cleanup temp address for user insertion
        if (newMail != 0) {
           // mailDao.deleteAddress(newMail);
        }
    }

    @Test
    public void getAllFriendLinks() throws Exception {

        assertEquals("user1 not created", bob1.getUuid(), userDao.getUser(newUser1).getUuid());
        assertEquals("user2 not created", bob2.getUuid(), userDao.getUser(newUser2).getUuid());
        assertEquals("user3 not created", bob3.getUuid(), userDao.getUser(newUser3).getUuid());

        assertEquals("user 1 not in link", bob1.getUuid(), link.getFriendid1());
        assertEquals("user 1 not in link", bob2.getUuid(), link.getFriendid2());

        newLink = dao.addFriendLink(link);

        assertEquals("link not created", link.getLinkid(), dao.getFriendLink(newLink).getLinkid());

        List<FriendLink> links = dao.getAllFriendLinks();
        assertTrue(links.size() > 0);
    }

    @Test
    public void getFriendLink() throws Exception {

    }

    @Test
    public void addFriendLink() throws Exception {
//        assertEquals("user1 not created", bob1.getUuid(), userDao.getUser(newUser1).getUuid());
   //     assertEquals("user2 not created", bob2.getUuid(), userDao.getUser(newUser2).getUuid());
    //    assertEquals("user3 not created", bob3.getUuid(), userDao.getUser(newUser3).getUuid());

        link.setFriendid1(1);
        link.setFriendid2(2);

        newLink = dao.addFriendLink(link);

        assertNotEquals("no user added", 0, newLink);


        assertEquals("link not created", link.getLinkid(), dao.getFriendLink(newLink).getLinkid());
    }

    @Test
    public void deleteFriendLink() throws Exception {

    }

    @Test
    public void updateFriendLink() throws Exception {

    }

}