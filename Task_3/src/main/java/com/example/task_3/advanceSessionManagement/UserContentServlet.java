package com.example.task_3.advanceSessionManagement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserContentServlet")
public class UserContentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Get the current session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);

        // Get the PrintWriter object to write the HTML response
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>User Content Servlet</title></head><body>");

        // Check if the user is logged in (session attribute "user" is present)
        if (session.getAttribute("user") != null) {
            // Display personalized content for the logged-in user
            String username = (String) session.getAttribute("user");
            out.println("<p>Welcome back, " + username + "!</p>");
            out.println("<p>This is your personalized content.</p>");
            out.println("<p><a href=\"logout\">Logout</a></p>");
        } else {
            // Prompt the user to log in
            out.println("<p>You are not logged in. Please <a href=\"simple\">log in</a>.</p>");
        }

        out.println("</body></html>");
    }
}