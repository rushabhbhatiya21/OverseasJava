package com.example.task_3.sessions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/timeout")
public class TimeOutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(5);
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Session Timeout Servlet</title></head><body>");

        if (session.isNew()) {
            out.println("<p>New session created. Session ID: " + session.getId() + "</p>");
        } else {
            out.println("<p>Existing session. Session ID: " + session.getId() + "</p>");
        }

        out.println("<p>Session timeout set to 5 seconds.</p>");

        // Check if the session is still valid
        if (isValid(session)) {
            out.println("<p>Session is still valid.</p>");
        } else {
            out.println("<p>Session has expired.</p>");
        }

        out.println("</body></html>");
    }

    private boolean isValid(HttpSession session) {
        try {

            session.getAttribute("dummyAttribute");
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }
}
