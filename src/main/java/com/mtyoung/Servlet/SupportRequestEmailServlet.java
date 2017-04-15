package com.mtyoung.Servlet;

import com.mtyoung.util.EmailMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Mike on 4/15/17.
 */
@WebServlet(
        name = "SupportRequest",
        urlPatterns = { "/supportrequest" }
)
public class SupportRequestEmailServlet  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session  = request.getSession();
        EmailMessage email = new EmailMessage();
        String message;

        String sender = request.getParameter("sender");
        String detail = request.getParameter("detail");

        if (email.sendSupportRequest(sender, detail)) {
            message = "Support Request Sent.";
        } else {
            message = "Support Request email failed to send.";
        }

        session.setAttribute("messagestatus", message);
        getServletContext().getRequestDispatcher("/support.jsp").forward(request, response);
    }
}
