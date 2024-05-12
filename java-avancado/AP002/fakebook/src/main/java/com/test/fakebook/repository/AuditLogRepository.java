package com.test.fakebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.AuditLog;

// Repository interface for AuditLog entity, extending JpaRepository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
