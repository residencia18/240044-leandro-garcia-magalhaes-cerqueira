package com.test.fakebook.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.test.fakebook.service.AuditService;

@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private AuditService auditService;

    // Thread-local variable to store client IP address
    private static ThreadLocal<String> clientIpAddress = new ThreadLocal<>();

    // Method to set client IP address
    public static void setClientIpAddress(String ipAddress) {
        clientIpAddress.set(ipAddress);
    }

    // Method to clear client IP address
    public static void clearClientIpAddress() {
        clientIpAddress.remove();
    }

    // Advice to log service access before method execution
    @Before("execution(* com.test.fakebook.service.*.*(..)) && !target(com.test.fakebook.service.AuditService)")
    public void logServiceAccess(JoinPoint joinPoint) {
        // Extract method name
        String methodName = joinPoint.getSignature().getName();
        // Define default description
        String description = "Method execution";
        // Initialize user ID and origin
        String userId = null;
        String origin = clientIpAddress.get();

        // Extract user ID from authentication context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            userId = user.getUsername();
        } else {
            // Set user ID as unauthenticated user if no authentication context found
            userId = "unauthenticated user";
        }

        // Log the event
        auditService.logEvent(methodName, description, userId, joinPoint.getTarget().getClass().getSimpleName(), origin);
    }
}
