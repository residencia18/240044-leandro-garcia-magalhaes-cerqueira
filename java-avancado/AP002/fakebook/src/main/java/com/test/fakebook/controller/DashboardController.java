package com.test.fakebook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {
	
    // Endpoint to retrieve dashboard information
    @GetMapping("/dashboard")
    public String dashboard() {
        // Default username is set to "Guest"
        String username = "Guest"; 
        
        // Retrieve authentication information from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Check if user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve username if user is authenticated
            username = authentication.getName();
        }
        
        // Return personalized greeting message
        return "Hello " + username;
    }
}
