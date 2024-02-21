package com.example.task_3.Combining;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


@WebFilter("/secured/*")
public class AuthenticationFilter implements Filter {

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if (isLoggedIn) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public AuthenticationFilter() {
        super();
    }

    public void destroy() {
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
