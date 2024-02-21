package com.example.task_3.asyncronousProcessing;

import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AsyncProcessingListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws java.io.IOException {
        // Called when asynchronous processing is complete
        System.out.println("Async processing completed for request with ID: " + event.getAsyncContext().getRequest().getLocalName());
    }

    @Override
    public void onError(AsyncEvent event) throws java.io.IOException {
        // Called when an error occurs during asynchronous processing
        System.err.println("Error during async processing for request with ID: " + event.getAsyncContext().getRequest().getLocalName());
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws java.io.IOException {
        // Called when asynchronous processing is started
        System.out.println("Async processing started for request with ID: " + event.getAsyncContext().getRequest().getLocalName());
    }

    @Override
    public void onTimeout(AsyncEvent event) throws java.io.IOException {
        // Called when the asynchronous operation times out
        System.err.println("Async processing timeout for request with ID: " + event.getAsyncContext().getRequest().getLocalName());
    }
}