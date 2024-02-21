package com.example.task_3.listener;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Created");
        HttpSession session = se.getSession();
        activeSessions++;
        System.out.println("Session created. Total active sessions: " + activeSessions);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Destroyed");
        HttpSession session = se.getSession();
        if (activeSessions > 0) {
            activeSessions--;
        }
        System.out.println("Session destroyed. Total active sessions: " + activeSessions);
    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}
