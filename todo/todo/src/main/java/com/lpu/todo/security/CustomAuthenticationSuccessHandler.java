package com.lpu.todo.security;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper; // For JSON response

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Customize your success response here
        Map<String, Object> responseBody = new HashMap<>();
        log.debug("form data {}", responseBody);
        responseBody.put("message", "Authentication successful!");
        responseBody.put("username", authentication.getName());
        // Add more data as needed, e.g., user roles, token (for JWT)

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK); // HTTP 200 OK
        response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        response.flushBuffer();
    }
}