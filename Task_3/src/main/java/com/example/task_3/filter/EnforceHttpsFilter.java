package com.example.task_3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/enforce-http-filter/*"})
public class EnforceHttpsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if (!httpRequest.isSecure() && httpRequest.getRequestURI().startsWith("/secure")) {
            String redirectUrl = "https://" + httpRequest.getServerName() + httpRequest.getRequestURI();
            httpResponse.sendRedirect(redirectUrl);
        } else {

            chain.doFilter(request, response);
        }
    }
    @Override
    public void destroy() {
    }
}
