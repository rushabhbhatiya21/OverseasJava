package com.example.task_3.Combining;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionManagerInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        SessionManagerInitializer sessionManager = new SessionManagerInitializer();
        servletContext.setAttribute("sessionManager", sessionManager);

        System.out.println("Session Manager initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Session Manager destroyed");
    }
}
