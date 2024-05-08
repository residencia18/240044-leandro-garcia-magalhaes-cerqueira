package com.test.fakebook.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.test.fakebook.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	Optional<PasswordResetToken> findByToken(String token);
	
	void deleteById(Long id);
	
	@Transactional
    @Modifying
    @Query("UPDATE password_reset_token prt SET prt.token = 'EXPIRED' WHERE prt.token = :token")
    void invalidateToken(String token);
	
		

}
