package com.example.task_3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@WebFilter("/*")
public class IpBlockingFilter implements Filter {

    private Set<String> blockedIps;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        blockedIps = new HashSet<>();
        blockedIps.add("192.168.0.1");
        blockedIps.add("10.0.0.2");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String ipAddress = request.getRemoteAddr();
        if (!blockedIps.contains(ipAddress)) {

            chain.doFilter(request, response);
        } else {
            response.getWriter().write("Access blocked for your IP address.");
        }
    }

    @Override
    public void destroy() {

    }
}
