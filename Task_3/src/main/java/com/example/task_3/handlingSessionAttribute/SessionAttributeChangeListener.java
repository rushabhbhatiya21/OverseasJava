package com.example.task_3.handlingSessionAttribute;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeChangeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        log("Attribute added - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        log("Attribute removed - Name: " + event.getName() + ", Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        log("Attribute replaced - Name: " + event.getName() +
                ", Old Value: " + event.getValue() +
                ", New Value: " + event.getSession().getAttribute(event.getName()));
    }

    private void log(String message) {
        // Log the message (you can customize this part based on your logging framework)
        System.out.println("[Session Attribute Change] " + message);
    }
}
