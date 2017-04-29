package com.mtyoung.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mike on 3/6/17.
 */
public class TextMessageTest {
    TextMessage txter;
    String messageBody;
    String senderPhone;

    @Before
    public void setUp() throws Exception {
        txter = new TextMessage();
        messageBody = "Does this Work?";
        senderPhone = "+16083334717";
    }

    @After
    public void tearDown() throws Exception {
        if (txter != null) {
            txter = null;
        }
    }

    @Test
    public void sendMessate() throws Exception {
        assertNotNull("message not sent", txter.sendMessage(senderPhone, messageBody));
    }

}