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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "audit_log")
public class AuditLog {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "EventName must not be null") // Validação no nível da aplicação
    @Size(min = 5, max = 100, message = "EventName must be between 5 and 100 characters long")
    @Column(unique = true, nullable = false, name = "event_name") // Restrições a nível de banco de dados
    private String eventName;
    
    @Column(name = "event_description")
    private String eventDescription;
    
    @Column(name = "timestamp")
    private Date timestamp;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "affected_resource")
    private String affectedResource;
    
    @Column(name = "origin")
    private String origin;
}