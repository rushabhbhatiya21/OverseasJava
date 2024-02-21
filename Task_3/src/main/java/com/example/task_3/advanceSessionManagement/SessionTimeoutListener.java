package com.example.task_3.advanceSessionManagement;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionTimeoutListener implements HttpSessionListener {

    private static final int MAX_IDLE_TIME_SECONDS = 300; // 5 minutes, adjust as needed

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Called when a new session is created
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Called when a session is about to be invalidated or timed out
    }

    private void logoutUser(HttpSession session) {
        // Perform actions to log out the user
        // For example, invalidate the session
        session.invalidate();

        // Additional actions like redirecting to a login page can be performed here
    }

    public static boolean isSessionIdle(HttpSession session) {
        long lastAccessedTime = session.getLastAccessedTime();
        long currentTime = System.currentTimeMillis();
        long idleTime = currentTime - lastAccessedTime;

        return idleTime >= (MAX_IDLE_TIME_SECONDS * 1000);
    }
}