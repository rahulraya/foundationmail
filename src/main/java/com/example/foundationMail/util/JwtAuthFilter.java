package com.example.foundationMail.util;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.foundationMail.Service.JwtService;
import com.example.foundationMail.Service.UserInfoService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter { 
    private final JwtService jwtService; 
    private final UserInfoService userDetailsService; 

    public JwtAuthFilter(JwtService jwtService, UserInfoService userDetailsService) { 
        this.jwtService = jwtService; 
        this.userDetailsService = userDetailsService; 
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { 
        String requestURI = request.getRequestURI();
        
        // Log the request URI
        System.out.println("Request URI: " + requestURI);

        // Bypass JWT filter for specific endpoints
        if (requestURI.equals("/emails/generateToken") || requestURI.equals("/h2-console/**") || requestURI.equals("/emails/register")) {
            System.out.println("Bypassing JWT for: " + requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization"); 
        String token = null; 
        String username = null; 
        if (authHeader != null && authHeader.startsWith("Bearer ")) { 
            token = authHeader.substring(7); 
            username = jwtService.extractUsername(token); 
        } 
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
            if (jwtService.validateToken(token, userDetails)) { 
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); 
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); 
                SecurityContextHolder.getContext().setAuthentication(authToken); 
            } 
        } 
        filterChain.doFilter(request, response); 
    }
}
