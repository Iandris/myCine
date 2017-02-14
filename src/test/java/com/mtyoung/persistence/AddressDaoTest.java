package com.mtyoung.persistence;

import com.mtyoung.entity.Address;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/14/17.
 */
public class AddressDaoTest {
    AddressDao dao;
    Address mail;
    int newMail = 0;

    @Before
    public void setup() {
        dao = new AddressDao();
        mail = new Address();

        mail.setStreetaddress1("605 Park St");
        mail.setStreetaddress2("");
        mail.setCity("Watertown");
        mail.setState(49);
        mail.setZipcode(53098);

    }

    @After
    public void cleanup() {
        if (newMail != 0) {
          dao.deleteAddress(newMail);
        }
    }

    @Test
    public void getAllAddresses() throws Exception {
        newMail = dao.addAddress(mail);
        List<Address> mails = dao.getAllAddresses();
        assertTrue(mails.size() > 0);
    }

    @Test
    public void getAddress() throws Exception {
        newMail = dao.addAddress(mail);
        assertEquals("ID from address not returned correctly", mail.getIdaddresses(), dao.getAddress(newMail).getIdaddresses());
        assertEquals("street 1 from address not returned correctly", mail.getStreetaddress1(), dao.getAddress(newMail).getStreetaddress1());
        assertEquals("street 2 from address not returned correctly", mail.getStreetaddress2(), dao.getAddress(newMail).getStreetaddress2());
        assertEquals("City from address not returned correctly", mail.getCity(), dao.getAddress(newMail).getCity());
        assertEquals("state from address not returned correctly", mail.getState(), dao.getAddress(newMail).getState());
        assertEquals("zipcode from address not returned correctly", mail.getZipcode(), dao.getAddress(newMail).getZipcode());
    }

    @Test
    public void addAddress() throws Exception {
        newMail = dao.addAddress(mail);
        assertNotEquals("user not added", 0, newMail);
        assertEquals("ID from address not inserted correctly", mail.getIdaddresses(), dao.getAddress(newMail).getIdaddresses());
        assertEquals("street 1 from address not inserted correctly", mail.getStreetaddress1(), dao.getAddress(newMail).getStreetaddress1());
        assertEquals("street 2 from address not inserted correctly", mail.getStreetaddress2(), dao.getAddress(newMail).getStreetaddress2());
        assertEquals("City from address not inserted correctly", mail.getCity(), dao.getAddress(newMail).getCity());
        assertEquals("state from address not inserted correctly", mail.getState(), dao.getAddress(newMail).getState());
        assertEquals("zipcode from address not inserted correctly", mail.getZipcode(), dao.getAddress(newMail).getZipcode());

    }

    @Test
    public void deleteAddress() throws Exception {
        dao.addAddress(mail);
        dao.deleteAddress(mail.getIdaddresses());
        assertNull("user not deleted", dao.getAddress(mail.getIdaddresses()));
    }

    @Test
    public void updateAddress() throws Exception {
        newMail = dao.addAddress(mail);

        mail.setStreetaddress1("205 Fairview St");
        mail.setStreetaddress2("Apt 555");
        mail.setCity("Madison");
        mail.setState(44);
        mail.setZipcode(12345);

        dao.updateAddress(mail);

        assertEquals("street 1 from address not updated correctly", mail.getStreetaddress1(), dao.getAddress(newMail).getStreetaddress1());
        assertEquals("street 2 from address not updated correctly", mail.getStreetaddress2(), dao.getAddress(newMail).getStreetaddress2());
        assertEquals("City from address not updated correctly", mail.getCity(), dao.getAddress(newMail).getCity());
        assertEquals("state from address not updated correctly", mail.getState(), dao.getAddress(newMail).getState());
        assertEquals("zipcode from address not updated correctly", mail.getZipcode(), dao.getAddress(newMail).getZipcode());
    }
}