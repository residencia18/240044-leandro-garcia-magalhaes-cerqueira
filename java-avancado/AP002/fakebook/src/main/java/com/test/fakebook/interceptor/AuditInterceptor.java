package com.test.fakebook.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.fakebook.service.AuditService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuditInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditService auditService;

    // Intercept and log requests before they are handled
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        
        // Get request origin IP address
        String origin = request.getHeader("X-FORWARDED-FOR");
        if (origin == null) {
            origin = request.getRemoteAddr();
        }

        // Get user ID from authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "unauthenticated user";
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            userId = user.getUsername();
        }

        // Generate event name and description
        String eventName = "Request to " + request.getRequestURI();
        String description = "Method: " + request.getMethod();

        // Log the event
        auditService.logEvent(eventName, description, userId, request.getRequestURI(), origin);
        return true;
    }

    // Perform actions after request handling but before view rendering
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    // Perform actions after request completion
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}
}
