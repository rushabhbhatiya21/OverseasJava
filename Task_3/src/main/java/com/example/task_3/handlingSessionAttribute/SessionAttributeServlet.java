package com.example.task_3.handlingSessionAttribute;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session-attribute-change")
public class SessionAttributeServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Add, remove, and replace session attributes for demonstration
        session.setAttribute("username", "john_doe");
        session.removeAttribute("username");
        session.setAttribute("email", "john@example.com");
        session.setAttribute("email", "john.doe@example.com");

        // For demonstration purposes, let's print the session attributes to the response
        response.getWriter().println("Check the console for session attribute change logs.");
    }
}
