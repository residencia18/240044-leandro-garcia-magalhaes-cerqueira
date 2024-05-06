package com.test.fakebook.repository;

import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.fakebook.entity.PasswordResetToken;
import lombok.RequiredArgsConstructor;


@Repository
@RequiredArgsConstructor
public class PasswordResetTokenRepository {
	
	private final JdbcClient jdbcClient;
	
	@Transactional(readOnly = true)
    public Optional<PasswordResetToken> findByToken(String token) {
        var findQuery = "SELECT id, token, user_id, expiry_date FROM password_reset_token WHERE token=:token";
        return jdbcClient.sql(findQuery).param("token", token).query(PasswordResetToken.class).optional();
    }
	
	@Transactional
    public void save(PasswordResetToken token) {
        var saveQuery = "INSERT INTO password_reset_token (token, user_id, expiry_date) VALUES (:token, :user_id, :expiry_date)";
        jdbcClient.sql(saveQuery)
                  .param("token", token.getToken())
                  .param("user_id", token.getUser())
                  .param("expiry_date", token.getExpiryDate())
                  .update();
    }
    
	 	@Transactional
	    public void delete(PasswordResetToken token) {
	        var deleteQuery = "DELETE FROM password_reset_token WHERE token=:token";
	        jdbcClient.sql(deleteQuery)
	                  .param("token", token.getToken())
	                  .update();
	    }
	    
	    @Transactional
	    public void invalidateToken(String token) {
	        var passwordResetToken = findByToken(token);
	        passwordResetToken.ifPresent(this::delete);
	    }
	


}