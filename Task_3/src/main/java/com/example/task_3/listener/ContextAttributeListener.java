package com.example.task_3.listener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;

public class ContextAttributeListener implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Context attribute added - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("Context attribute removed - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("Context attribute replaced - Name: " + event.getName() + ", Old Value: " +
                event.getValue() + ", New Value: " + event.getServletContext().getAttribute(event.getName()));
    }
}
