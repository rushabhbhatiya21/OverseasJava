package com.example.task_3.config;

import com.example.task_3.component.RequestLoggingFilter;
import com.example.task_3.listener.ContextAttributeListener;
import com.example.task_3.listener.ContextListener;
import com.example.task_3.listener.SessionAttributeListener;
import com.example.task_3.listener.SessionListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListener() {
        ServletListenerRegistrationBean<SessionListener> sessionListenerBean =
                new ServletListenerRegistrationBean<>();
        sessionListenerBean.setListener(new SessionListener());
        return sessionListenerBean;
    }
    @Bean
    public ServletListenerRegistrationBean<ContextListener> contextListener() {
        ServletListenerRegistrationBean<ContextListener> contextListenerBean =
                new ServletListenerRegistrationBean<>();
        contextListenerBean.setListener(new ContextListener());
        return contextListenerBean;
    }
    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // You can customize URL patterns if needed
        return registrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean<SessionAttributeListener> sessionAttributeListener() {
        ServletListenerRegistrationBean<SessionAttributeListener> registrationBean =
                new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new SessionAttributeListener());
        return registrationBean;
    }
    @Bean
    public ServletListenerRegistrationBean<ContextAttributeListener> contextAttributeListener() {
        ServletListenerRegistrationBean<ContextAttributeListener> registrationBean =
                new ServletListenerRegistrationBean<>();
        registrationBean.setListener(new ContextAttributeListener());
        return registrationBean;
    }
}