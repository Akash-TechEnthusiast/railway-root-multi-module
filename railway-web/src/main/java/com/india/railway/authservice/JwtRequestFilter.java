package com.india.railway.authservice;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.india.railway.service.mysql.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // Get the JWT token from the Authorization header
        String header = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7); // Remove "Bearer " prefix
            System.out.println("Received token   " + token);
            username = jwtUtils.getUsernameFromToken(token); // Get username from token
        }

        // If we have a valid token and no authentication is set in the context
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validate the token
            if (jwtUtils.validateToken(token)) {
                // Create authentication token and set it in SecurityContextHolder
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

              }
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }
}
