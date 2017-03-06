package com.mtyoung.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Mike on 3/6/17.
 */
public class EmailMessage {
    //TODO update username and password for your gmail account
    private static final String GMAIL_USERNAME = "";
    private static final String GMAIL_PWD = "";


    public boolean sendFriendRequest(String sender, String recipient) {
        boolean success = false;

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");

        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(GMAIL_USERNAME,GMAIL_PWD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("FriendRequest@MyCine.com", "MyCine"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("New MyCine Friend Request");
            //TODO see about adding html with accept link
            message.setText(sender + " would like to be your friend on MyCine and share their movie library with you.");

            Transport.send(message);
            success = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return success;
    }
}
