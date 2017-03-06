package com.mtyoung.persistence;

import com.mtyoung.entity.Friendrequests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mike on 3/6/17.
 */
public class FriendrequestsDaoTest {
    FriendrequestsDao dao;
    Friendrequests request;
    int newRequest = 0;

    @Before
    public void setUp() throws Exception {
        dao = new FriendrequestsDao();
        request = new Friendrequests();
        request.setReqid("6B86B273FF34FCE19D6B804EFF5A3F5747ADA4EAA22F1D49C01E52DDB7875B4B");
        request.setUsera(1);
        request.setUserb(2);
    }

    @After
    public void tearDown() throws Exception {
        if (newRequest != 0) {
            dao.deleteFriendRequest(newRequest);
        }
    }

    @Test
    public void getAllFriendRequests() throws Exception {
        newRequest = dao.addFriendRequest(request);
        assertTrue("no friend requests found", dao.getAllFriendRequests().size() > 0);
    }

    @Test
    public void getFriendRequest() throws Exception {
        newRequest = dao.addFriendRequest(request);
        assertEquals("incorrect request ID returned", request.getReqid(), dao.getFriendRequest(newRequest).getReqid());
        assertEquals("incorrect sender id returned", request.getUsera(), dao.getFriendRequest(newRequest).getUsera());
        assertEquals("incorrect recipient id returned", request.getUserb(), dao.getFriendRequest(newRequest).getUserb());

    }

    @Test
    public void addFriendRequest() throws Exception {
        newRequest = dao.addFriendRequest(request);
        assertEquals("incorrect request ID inserted", request.getReqid(), dao.getFriendRequest(newRequest).getReqid());
        assertEquals("incorrect sender id inserted", request.getUsera(), dao.getFriendRequest(newRequest).getUsera());
        assertEquals("incorrect recipient id inserted", request.getUserb(), dao.getFriendRequest(newRequest).getUserb());

    }

    @Test
    public void deleteFriendRequest() throws Exception {
        dao.addFriendRequest(request);
        dao.deleteFriendRequest(request.getIdfriendrequests());
        assertNull("request not deleted", dao.getFriendRequest(request.getIdfriendrequests()));
    }

    @Test
    public void updateFriendRequest() throws Exception {
        newRequest = dao.addFriendRequest(request);
        assertEquals("incorrect request ID returned", request.getReqid(), dao.getFriendRequest(newRequest).getReqid());
        assertEquals("incorrect sender id returned", request.getUsera(), dao.getFriendRequest(newRequest).getUsera());
        assertEquals("incorrect recipient id returned", request.getUserb(), dao.getFriendRequest(newRequest).getUserb());

        request.setUsera(2);
        request.setUserb(3);
        request.setReqid("lasdkdkalkalslslsksdkdiekksksidsdfs");

        dao.updateFriendRequest(request);

        assertEquals("incorrect request ID updated", request.getReqid(), dao.getFriendRequest(newRequest).getReqid());
        assertEquals("incorrect sender id updated", request.getUsera(), dao.getFriendRequest(newRequest).getUsera());
        assertEquals("incorrect recipient id updated", request.getUserb(), dao.getFriendRequest(newRequest).getUserb());


    }

    @Test
    public void findFriendRequestByHashId() throws  Exception {
        newRequest = dao.addFriendRequest(request);
        assertNotNull("request not found by hash id", dao.findFriendRequestByHashId(request.getReqid()));

    }


}