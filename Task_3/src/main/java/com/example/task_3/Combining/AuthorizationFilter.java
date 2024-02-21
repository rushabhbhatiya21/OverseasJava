package com.example.task_3.Combining;
import jakarta.servlet.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

@WebFilter("/secured/*")
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
        super();
    }

    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (isUserAuthorized(session)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied");
        }
    }

    private boolean isUserAuthorized(HttpSession session) {
        return (session != null && session.getAttribute("user") != null
                && "admin".equals(session.getAttribute("userRole")));
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void destroy() {
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
