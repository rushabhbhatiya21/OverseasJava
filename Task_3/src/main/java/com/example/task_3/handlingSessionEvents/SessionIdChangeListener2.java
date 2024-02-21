package com.example.task_3.handlingSessionEvents;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionIdListener;

@WebListener
public class SessionIdChangeListener2 implements HttpSessionIdListener {
    @Override
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        log("Session ID changed - Old Session ID: " + oldSessionId + ", New Session ID: " + event.getSession().getId());
    }

    private void log(String message) {
        // Log the message (you can customize this part based on your logging framework)
        System.out.println("[Session ID Change] " + message);
    }
}
