package com.example.task_3.sessions;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/simple")
public class SimpleServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(true);
        String storedData = (String) session.getAttribute("data");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Data Servlet</title></head><body>");

        if (storedData != null) {
            out.println("<p>Retrieved data from the session: " + storedData + "</p>");
        } else {
            out.println("<p>No data stored in the session</p>");
        }
        String newData = "Hello, Session!";
        session.setAttribute("data", newData);
        session.setAttribute("user","User1");

        out.println("<p>Stored data in the session: " + newData + "</p>");

        out.println("</body></html>");
    }
}
