package com.mtyoung.util;

import com.mtyoung.entity.Friendrequests;
import com.mtyoung.entity.User;
import com.mtyoung.persistence.FriendrequestsDao;
import org.apache.catalina.realm.RealmBase;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Mike on 3/6/17.
 */
public class EmailMessage {
    //TODO update username and password for your gmail account
    private static final String GMAIL_USERNAME = "iandris427@gmail.com";
    private static final String GMAIL_PWD = "J795ui9j";
    private Message message = new MimeMessage(getEmailSession());

    public boolean sendFriendRequest(User sender, User recipient) {
        boolean success = false;

        try {
            this.message.setFrom(new InternetAddress("FriendRequest@MyCine.com", "MyCine"));
            this.message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getUser_name()));
            this.message.setSubject("New MyCine Friend Request");
            //TODO do not use html/body tags use only inner elements
            String senderName = sender.getFname() + " " + sender.getLname();
            FriendrequestsDao dao = new FriendrequestsDao();
            Friendrequests friendrequests = new Friendrequests();
            Random rand = new Random();


            String hashedReqid = RealmBase.Digest(String.valueOf(rand.nextInt(1000000000 - 1 + 1) + 1),"sha-256", "UTF-8");
            friendrequests.setReqid(hashedReqid);
            friendrequests.setUsera(sender.getUuid());
            friendrequests.setUserb(recipient.getUuid());
            dao.addFriendRequest(friendrequests);

            String htmlBody = "<link rel='stylesheet' type='text/css' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>";
            htmlBody += "<h3>MyCine</h3><br /><form action='' method='post'><p>" + senderName + " would like to be your friend on MyCine and share their movie library with you.</p>";
            htmlBody += "<br /><p>To Accept this friend request click <a href='http://52.14.69.207:8080/mycine/secure/auth/acceptfriendrequest?reqid=" + hashedReqid +  "' class='btn btn-success'>Approve</a>";

            this.message.setContent(htmlBody, "text/html; charset=utf-8");

            Transport.send(this.message);
            success = true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean sendSupportRequest(String sender, String message) {
        boolean success = false;

        try {
            this.message.setFrom(new InternetAddress("Support@MyCine.com", "MyCine"));
            this.message.setRecipient(Message.RecipientType.TO, new InternetAddress("myoung86@charter.net"));
            this.message.setSubject("New MyCine Friend Support Request");

            String htmlBody = "<link rel='stylesheet' type='text/css' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>";
            htmlBody += "<h3>MyCine</h3><br /><p>" + sender + " has requested support.</p>";
            htmlBody += "<br /><p>"+ message + "</p>";

            this.message.setContent(htmlBody, "text/html; charset=utf-8");

            Transport.send(this.message);
            success = true;


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return success;
    }


    private Session getEmailSession() {
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

        return session;
    }
}
