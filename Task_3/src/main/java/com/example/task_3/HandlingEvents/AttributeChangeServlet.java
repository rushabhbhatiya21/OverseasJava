package com.example.task_3.HandlingEvents;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/attribute-change")
public class AttributeChangeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");

        // Get the current session or create a new one if it doesn't exist
        HttpSession session = request.getSession(true);

        // Create an instance of AttributeChangeListener for the attribute "exampleAttribute"
        AttributeChangeListener listener = new AttributeChangeListener("exampleAttribute");

        // Add the listener to the session attribute
        session.setAttribute("exampleAttribute", listener);

        // Modify the attribute to trigger the listener
        session.setAttribute("exampleAttribute", "new value");

        // Remove the attribute to trigger the listener
        session.removeAttribute("exampleAttribute");
    }
}
