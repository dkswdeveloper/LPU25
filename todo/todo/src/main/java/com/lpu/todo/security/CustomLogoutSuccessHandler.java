package com.lpu.todo.security;
import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lpu.todo.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
	
	public void converter()
	{
		User user = new User(); user.setUsername("Renu"); user.setPassword("renu");
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(user);
			User value = mapper.readValue(jsonString, User.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	
	

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // Perform custom actions after successful logout
        // Example: Log the logout event
        if (authentication != null) {
            System.out.println("User " + authentication.getName() + " successfully logged out.");
        }

        // Example: Send a custom JSON response
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\": \"Logout successful\"}");

        // Alternatively, redirect to a specific URL
        // response.sendRedirect("/logout-success");
    }
}






