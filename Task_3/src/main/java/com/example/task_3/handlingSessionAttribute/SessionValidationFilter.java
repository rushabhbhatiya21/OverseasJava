package com.example.task_3.handlingSessionAttribute;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class SessionValidationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        // Validate and sanitize specific session attributes
        validateAndSanitizeAttribute(session, "username");
        validateAndSanitizeAttribute(session, "email");

        // Continue the request-response chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void validateAndSanitizeAttribute(HttpSession session, String attributeName) {
        Object attributeValue = session.getAttribute(attributeName);

        // Check if the attribute exists and is a String
        if (attributeValue instanceof String) {
            // Sanitize the attribute value (replace dangerous characters)
            String sanitizedValue = sanitizeString((String) attributeValue);

            // Set the sanitized value back to the session
            session.setAttribute(attributeName, sanitizedValue);
        }
    }

    private String sanitizeString(String input) {
        // Implement your sanitization logic here
        // This is a basic example; you may want to use a library for more robust sanitization
        return input.replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
