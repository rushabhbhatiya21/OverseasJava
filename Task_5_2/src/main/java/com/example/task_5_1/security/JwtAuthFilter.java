package com.example.task_5_1.security;

import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserInfoDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("req");
        final String authHeader = request.getHeader(AUTHORIZATION);
        System.out.println(authHeader);
//        Enumeration<String> s = request.getHeaderNames();

//        while(s.hasMoreElements()){
//            String x = s.nextElement();
//            System.out.println(x + " = " + request.getHeader(x));
//        }

        final String userName;
        final String jwtToken;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
//            System.out.println("inside if...");
            filterChain.doFilter(request, response);
            return;
        }
//        System.out.println("outside if...");
        jwtToken = authHeader.substring(7);
        System.out.println("JWT Token: ");
        System.out.println(jwtToken);
        userName = jwtUtil.extractUsername(jwtToken);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);

    }
}
