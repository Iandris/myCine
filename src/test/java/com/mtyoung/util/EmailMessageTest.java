package com.mtyoung.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mike on 3/6/17.
 */
public class EmailMessageTest {
    EmailMessage email;
    String recipient;
    String senderName;

    @Before
    public void setUp() throws Exception {
        email = new EmailMessage();
        recipient = "mtyoung@madisoncollege.edu";
        senderName = "Mike Young";
    }

    @After
    public void tearDown() throws Exception {
        if (email != null) {
            email = null;
        }
    }

    @Test
    public void sendFriendRequest() throws Exception {
        assertEquals("message not successfully sent", true, email.sendFriendRequest(senderName, recipient));
    }

}