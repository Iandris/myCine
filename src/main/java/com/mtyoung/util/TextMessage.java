package com.mtyoung.util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Created by Mike on 3/6/17.
 */
public class TextMessage {
    public static final String ACCOUNT_SID = "ACb2dc1ae02fb410adf2d107f01fc1f708";
    public static final String AUTH_TOKEN = "7cced0c148f37ed828170f8a10b37d8f";
    public static final String SENDER_PHONE = "+16082607041";

    public String sendMessate(String txtRecipient, String txtBody) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(txtRecipient),
                new PhoneNumber(SENDER_PHONE),
                txtBody).create();

        return message.getSid();
    }
}
