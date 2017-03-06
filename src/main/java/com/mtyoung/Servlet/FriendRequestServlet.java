package com.mtyoung.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "FriendRequest",
        urlPatterns = { "/secure/auth/friendrequest" }
)

/**
 * Created by Mike on 3/6/17.
 */
public class FriendRequestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

    }
}
