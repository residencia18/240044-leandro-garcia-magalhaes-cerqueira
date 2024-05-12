package com.test.fakebook.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.fakebook.entity.AuditLog;
import com.test.fakebook.repository.AuditLogRepository;

// Service class for logging audit events
@Service
public class AuditService {
    // Autowired repository for audit logs
    @Autowired
    private AuditLogRepository auditLogRepository;

    // Method to log an audit event
    public void logEvent(String eventName, String description, String userId, String resource, String origin) {
        // Create a new AuditLog object
        AuditLog log = new AuditLog();
        // Set event details
        log.setEventName(eventName);
        log.setEventDescription(description);
        log.setTimestamp(new Date()); 
        log.setUserId(userId); 
        log.setAffectedResource(resource); 
        log.setOrigin(origin); 
        // Save the audit log
        auditLogRepository.save(log);
    }
}

