package com.example.task_3.advanceSessionManagement;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = "/*",asyncSupported = true)
public class MaxSessionsFilter implements Filter {

    private static final int MAX_SESSIONS_PER_USER = 2; // Adjust this value as needed
    private final Map<String, Integer> activeSessionsPerUser = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getSession(false) != null) {
            String username = getUsernameFromSession(httpRequest.getSession());

            if (username != null) {
                int activeSessions = getActiveSessionsForUser(username);

                if (activeSessions >= MAX_SESSIONS_PER_USER) {
                    request.getRequestDispatcher("/sessionLimitExceeded.jsp").forward(request, response);
                    return;
                } else {
                    setActiveSessionsForUser(username, activeSessions + 1);
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private String getUsernameFromSession(HttpSession session) {
        return (String) session.getAttribute("user");
    }

    private int getActiveSessionsForUser(String username) {
        return activeSessionsPerUser.getOrDefault(username, 0);
    }

    private void setActiveSessionsForUser(String username, int activeSessions) {
        activeSessionsPerUser.put(username, activeSessions);
    }
}