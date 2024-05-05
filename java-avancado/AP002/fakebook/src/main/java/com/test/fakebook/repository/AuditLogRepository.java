package com.test.fakebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.AuditLog;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}