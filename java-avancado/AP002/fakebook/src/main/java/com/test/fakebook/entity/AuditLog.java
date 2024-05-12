package com.test.fakebook.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity class representing an audit log
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "audit_log") // Specifies the entity name in the database
public class AuditLog {
    
    // Primary key for the audit log
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Validation constraints for event name
    @NotNull(message = "EventName must not be null") 
    @Size(min = 5, max = 100, message = "EventName must be between 5 and 100 characters long")
    @Column(unique = true, nullable = false, name = "event_name") // Specifies column attributes in the database
    private String eventName;
    
    // Column for event description
    @Column(name = "event_description")
    private String eventDescription;
    
    // Column for timestamp
    @Column(name = "timestamp")
    private Date timestamp;
    
    // Column for user ID
    @Column(name = "user_id")
    private String userId;
    
    // Column for affected resource
    @Column(name = "affected_resource")
    private String affectedResource;
    
    // Column for origin
    @Column(name = "origin")
    private String origin;
}
