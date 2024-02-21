package com.example.task_3.Combining;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;



@WebListener
public class SessionTrackingListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Session created event
        HttpSession session = se.getSession();
        System.out.println("Session Created: " + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Session destroyed event
        HttpSession session = se.getSession();
        Object user = session.getAttribute("user");

        if (user != null) {
            System.out.println("Logout Event: User " + user + " logged out from Session " + session.getId());
        } else {
            System.out.println("Session Destroyed: " + session.getId());
        }
    }
}
