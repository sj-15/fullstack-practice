package com.udemy.easybank.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Populate dynamic values
        LocalDateTime currentDateTime = LocalDateTime.now();
        String message = (authException != null && authException.getMessage() != null) ? authException.getMessage() : "Unauthorized access";
        String path = request.getRequestURI();

        // Set custom header
        response.setHeader("Custom-header", "Authentication failed!");

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Custom JSON response
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write("{\"timestamp\": \"" + currentDateTime + "\", \"status\": " + HttpStatus.UNAUTHORIZED.value() + ", \"message\": \"" + message + "\", \"path\": \"" + path + "\"}");
    }
}
