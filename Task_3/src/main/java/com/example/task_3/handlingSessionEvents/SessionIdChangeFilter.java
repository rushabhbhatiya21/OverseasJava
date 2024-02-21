package com.example.task_3.handlingSessionEvents;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class SessionIdChangeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code goes here, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();

        // Check if the session ID has changed since the last request
        if (session.isNew()) {
            // Session ID changed; invoke necessary logic
            handleSessionIdChange(session.getId());
        }

        // Continue the request-response chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code goes here, if needed
    }

    private void handleSessionIdChange(String newSessionId) {
        // Implement logic to handle session ID change
        // For demonstration purposes, let's print a message to the console
        System.out.println("[Session ID Change Filter] Session ID changed to: " + newSessionId);
    }
}
