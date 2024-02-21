package com.example.task_3.sessions;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionDurationListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session is created, record the start time
        HttpSession session = se.getSession();
        session.setAttribute("startTime", System.currentTimeMillis());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session is destroyed, calculate and log the duration
        HttpSession session = se.getSession();
        long startTime = (long) session.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // Log or store the duration as needed (for example, you can use a logging framework)
        System.out.println("User with session ID " + session.getId() + " spent " + duration + " milliseconds on the website.");
    }
}
