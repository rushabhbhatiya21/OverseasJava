package com.example.task_3.advanceSessionManagement;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebFilter(urlPatterns = "/*",asyncSupported = true)
public class SessionFixationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession();

        // Check if a new session is being created or if the request is an initial login
        if (session.isNew() || !session.getAttribute("sessionIdValidated").equals("true")) {
            // Generate a new session ID
            String newSessionId = generateNewSessionId();

            // Store the old session attributes
            Enumeration<String> attributeNames = session.getAttributeNames();
            Map<String, Object> attributes = new HashMap<>();

            while (attributeNames.hasMoreElements()) {
                String attributeName = attributeNames.nextElement();
                attributes.put(attributeName, session.getAttribute(attributeName));
            }

            // Invalidate the old session and create a new one with the new session ID
            session.invalidate();
            session = httpRequest.getSession(true);

            // Restore the old session attributes to the new session
            for (Map.Entry<String, Object> entry : attributes.entrySet()) {
                session.setAttribute(entry.getKey(), entry.getValue());
            }

            // Set a flag indicating that the session ID has been validated
            session.setAttribute("sessionIdValidated", "true");

            // Set the new session ID in the response cookie
            httpResponse.setHeader("Set-Cookie", "JSESSIONID=" + newSessionId + "; HttpOnly; SameSite=Strict");
        }

        // Continue with the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }

    private String generateNewSessionId() {
        // Generate a new random session ID (you may use a more secure method)
        return UUID.randomUUID().toString().replace("-", "");
    }
}