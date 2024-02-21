package com.example.task_3.HandlingEvents;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class AttributeChangeListener implements HttpSessionBindingListener {

    private String attributeName;

    public AttributeChangeListener(String attributeName) {
        this.attributeName = attributeName;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        // Attribute is added to the session
        System.out.println("Attribute '" + attributeName + "' added to the session.");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        // Attribute is removed from the session
        System.out.println("Attribute '" + attributeName + "' removed from the session.");
    }


    public String getAttributeName() {
        return attributeName;
    }

}
