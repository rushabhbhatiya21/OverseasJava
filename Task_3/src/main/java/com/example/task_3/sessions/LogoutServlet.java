package com.example.task_3.sessions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Logout Servlet</title></head><body>");

        if (session != null) {
            session.invalidate();
            out.println("<p>Logout successful. Session invalidated.</p>");

        } else {
            out.println("<p>No active session. You are already logged out.</p>");
        }

        out.println("</body></html>");
    }
}
