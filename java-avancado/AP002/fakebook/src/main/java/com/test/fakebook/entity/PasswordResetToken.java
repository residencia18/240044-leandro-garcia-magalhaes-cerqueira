package com.test.fakebook.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "password_reset_token")
public class PasswordResetToken {
 
    
	private static final int EXPIRATION = 60 * 24;
	
	public static int getExpiration() {
		return EXPIRATION;
	}
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String token;
 
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

 
    @Column(name = "expiry_date")
    private Date expiryDate;
}
