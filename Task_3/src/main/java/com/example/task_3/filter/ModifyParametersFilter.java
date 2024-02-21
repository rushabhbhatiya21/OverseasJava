package com.example.task_3.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.io.IOException;

@WebFilter("/modify2/*")
public class ModifyParametersFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Enumeration<String> parameterNames = httpRequest.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = httpRequest.getParameterValues(paramName);
            HttpServletRequest req = (HttpServletRequest) request;


            for (int i = 0; i < paramValues.length; i++) {
                paramValues[i] = paramValues[i] + "_modified";
            }


            httpRequest.setAttribute(paramName, paramValues);
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}