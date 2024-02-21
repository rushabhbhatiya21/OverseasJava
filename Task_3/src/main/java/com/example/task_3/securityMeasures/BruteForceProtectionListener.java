package com.example.task_3.securityMeasures;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class BruteForceProtectionListener implements HttpSessionListener {

    private static final int MAX_LOGIN_ATTEMPTS = 3;
    private static final long BLOCK_DURATION = 5 * 60 * 1000; // 5 minutes in milliseconds

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session is created
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session is destroyed
    }

    public static void registerLoginAttempt(HttpSession session) {
        Integer loginAttempts = (Integer) session.getAttribute("loginAttempts");

        if (loginAttempts == null) {
            // First login attempt
            session.setAttribute("loginAttempts", 1);
        } else {
            // Increment login attempts
            session.setAttribute("loginAttempts", loginAttempts + 1);

            if (loginAttempts + 1 >= MAX_LOGIN_ATTEMPTS) {
                // Block the session for a specified duration
                blockSession(session);
            }
        }
    }

    private static void blockSession(HttpSession session) {
        session.setAttribute("blocked", true);
        session.setMaxInactiveInterval((int) BLOCK_DURATION / 1000); // Set session timeout to block duration
    }

    public static boolean isSessionBlocked(HttpSession session) {
        return session.getAttribute("blocked") != null && (boolean) session.getAttribute("blocked");
    }
}