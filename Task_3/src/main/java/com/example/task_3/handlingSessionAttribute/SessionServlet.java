package com.example.task_3.handlingSessionAttribute;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session-validation")
public class SessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Set some session attributes with potential security issues
        session.setAttribute("username", "<script>alert('XSS attack');</script>");
        session.setAttribute("email", "malicious@example.com");

        // For demonstration purposes, let's print the session attributes to the response
        response.getWriter().println("Username: " + session.getAttribute("username") + "<br>");
        response.getWriter().println("Email: " + session.getAttribute("email"));
    }
}
