package com.test.fakebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.test.fakebook.entity.PasswordResetToken;

// Repository interface for PasswordResetToken entity, extending JpaRepository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // Method to find a PasswordResetToken by its token
    Optional<PasswordResetToken> findByToken(String token);

    // Method to delete a PasswordResetToken by its ID
    void deleteById(Long id);

    // Method to delete a PasswordResetToken by its token
    @Transactional
    @Modifying
    void deleteByToken(String token);

    // Method to invalidate a token by updating its value to 'EXPIRED'
    @Transactional
    @Modifying
    @Query("UPDATE password_reset_token prt SET prt.token = 'EXPIRED' WHERE prt.token = :token")
    void invalidateToken(String token);
}

