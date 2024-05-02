package com.example.springsecurity.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.test.fakebook.entity.AuditLog;
import com.test.fakebook.repository.AuditLogRepository;



@DataJpaTest
public class AuditLogRepositoryTest {

    @Autowired
    private AuditLogRepository repository;

    @Test
    public void testSaveAuditLog() {
        AuditLog log = new AuditLog();
        log.setEventName("LoginAttempt");
        log.setEventDescription("User login attempt");
        log.setTimestamp(new Date());
        log.setUserId("user1");
        log.setAffectedResource("LoginService");
        log.setOrigin("127.0.0.1");

        AuditLog savedLog = repository.save(log);
        assertThat(savedLog).isNotNull();
        assertThat(savedLog.getId()).isNotNull();
    }
}
