package com.mtyoung.persistence;

import com.mtyoung.entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class MessageDaoTest {

    Message message;
    MessageDao dao;
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
    int newMessage = 0;

    @Before
    public void setup() {
        dao = new MessageDao();
        message = new Message();
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
        bob1.setAddressid(newMail);
        bob1.setUser_name("bob11@email.com");
        bob1.setCellnumber("0000000008");
        bob1.setReminderthreshold(1);
        bob1.setDefaultrentalperiod(3);
        bob1.setPassword("Password");
        newUser1 = userDao.addUser(bob1);

        bob2 = new User();
        bob2.setFname("John");
        bob2.setLname("Smith");
        bob2.setAddressid(newMail);
        bob2.setUser_name("bob12@email.com");
        bob2.setCellnumber("0000000007");
        bob2.setReminderthreshold(1555);
        bob2.setDefaultrentalperiod(39);
        bob2.setPassword("Password");
        newUser2 = userDao.addUser(bob2);

        bob3 = new User();
        bob3.setFname("Dave");
        bob3.setLname("Navarro");
        bob3.setAddressid(newMail);
        bob3.setUser_name("bob13@email.com");
        bob3.setCellnumber("0000000006");
        bob3.setReminderthreshold(51123);
        bob3.setDefaultrentalperiod(123123);
        bob3.setPassword("Password");
        newUser3 = userDao.addUser(bob3);

        message.setSenderid(bob1.getUuid());
        message.setRecipientid(bob2.getUuid());
        message.setDatetime(LocalDateTime.of(2017,2,15,10,39,25));
        message.setMessagebody("HEY BUD YOU'RE LATE");
    }

    @After
    public void cleanup() {
        if (newMessage != 0) {
            dao.deleteMessage(newMessage);
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
    public void getAllMessages() throws Exception {
        newMessage = dao.addMessage(message);
        List<Message> messages = dao.getAllMessages();
        assertNotEquals("no messages returned", 0, messages.size());
    }

    @Test
    public void getMessage() throws Exception {
        newMessage = dao.addMessage(message);
        assertEquals("message ID not returned" , message.getIdmessagelog(), dao.getMessage(newMessage).getIdmessagelog());
        assertEquals("message sender not returned" , message.getSenderid(), dao.getMessage(newMessage).getSenderid());
        assertEquals("message recipient not returned" , message.getRecipientid(), dao.getMessage(newMessage).getRecipientid());
        assertEquals("message body not returned" , message.getMessagebody(), dao.getMessage(newMessage).getMessagebody());
        assertEquals("message datetime not returned" , message.getDatetime(), dao.getMessage(newMessage).getDatetime());
    }

    @Test
    public void addMessage() throws Exception {
        newMessage = dao.addMessage(message);
        assertEquals("message ID not inserted" , message.getIdmessagelog(), dao.getMessage(newMessage).getIdmessagelog());
        assertEquals("message sender not inserted" , message.getSenderid(), dao.getMessage(newMessage).getSenderid());
        assertEquals("message recipient not inserted" , message.getRecipientid(), dao.getMessage(newMessage).getRecipientid());
        assertEquals("message body not inserted" , message.getMessagebody(), dao.getMessage(newMessage).getMessagebody());
        assertEquals("message datetime not inserted" , message.getDatetime(), dao.getMessage(newMessage).getDatetime());

    }

    @Test
    public void deleteMessage() throws Exception {
        dao.addMessage(message);
        dao.deleteMessage(message.getIdmessagelog());
        assertNull("message not deleted", dao.getMessage(message.getIdmessagelog()));
    }

    @Test
    public void updateMessage() throws Exception {
        newMessage = dao.addMessage(message);

        message.setSenderid(bob2.getUuid());
        message.setRecipientid(bob3.getUuid());
        message.setDatetime(LocalDateTime.of(2017,1,1,1,1,1));
        message.setMessagebody("HEY BUD YOU'RE Updated");

        dao.updateMessage(message);

        assertEquals("message ID not updated" , message.getIdmessagelog(), dao.getMessage(newMessage).getIdmessagelog());
        assertEquals("message sender not updated" , message.getSenderid(), dao.getMessage(newMessage).getSenderid());
        assertEquals("message recipient not updated" , message.getRecipientid(), dao.getMessage(newMessage).getRecipientid());
        assertEquals("message body not updated" , message.getMessagebody(), dao.getMessage(newMessage).getMessagebody());
        assertEquals("message datetime not updated" , message.getDatetime(), dao.getMessage(newMessage).getDatetime());

    }

}