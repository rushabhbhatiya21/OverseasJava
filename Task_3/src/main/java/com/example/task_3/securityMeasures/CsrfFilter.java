package com.example.task_3.securityMeasures;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

@WebFilter(urlPatterns = "/*",asyncSupported = true)
public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the request is a POST request
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
            // Validate CSRF token
            if (!isValidCsrfToken(httpRequest)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
                return;
            }
        }

        // Continue with the filter chain
        chain.doFilter(request, response);

        // Add or update CSRF token in the session after the response is processed
        updateCsrfToken(httpRequest);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }

    private boolean isValidCsrfToken(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Retrieve the CSRF token from the session
            String sessionCsrfToken = (String) session.getAttribute("csrfToken");

            // Retrieve the CSRF token from the request
            String requestCsrfToken = request.getParameter("csrfToken");

            // Check if the CSRF tokens match
            return sessionCsrfToken != null && sessionCsrfToken.equals(requestCsrfToken);
        }

        return false;
    }

    private void updateCsrfToken(HttpServletRequest request) {
        HttpSession session = request.getSession(true);

        // Generate a new CSRF token
        String csrfToken = generateCsrfToken();

        // Store the CSRF token in the session
        session.setAttribute("csrfToken", csrfToken);

        // Add the CSRF token as a request attribute for JSP pages or other views
        request.setAttribute("csrfToken", csrfToken);
    }

    private String generateCsrfToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}