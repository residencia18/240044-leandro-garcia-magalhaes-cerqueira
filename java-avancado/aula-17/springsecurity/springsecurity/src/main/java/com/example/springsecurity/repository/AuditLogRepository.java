package com.example.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}