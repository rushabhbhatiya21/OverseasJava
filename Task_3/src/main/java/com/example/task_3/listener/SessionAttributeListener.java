package com.example.task_3.listener;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;


public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Session attribute added - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Session attribute removed - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Session attribute replaced - Name: " + event.getName() + ", Old Value: " +
                event.getValue() + ", New Value: " + event.getSession().getAttribute(event.getName()));
    }
}
