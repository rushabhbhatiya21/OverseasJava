package com.example.task_3.config;

import com.example.task_3.controller.HelloServlet;
import jakarta.servlet.http.HttpServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean<HttpServlet> helloServlet(){
        ServletRegistrationBean<HttpServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new HelloServlet());
        registrationBean.addUrlMappings("/hello");
        registrationBean.setLoadOnStartup(1);

        return registrationBean;
    }




}
