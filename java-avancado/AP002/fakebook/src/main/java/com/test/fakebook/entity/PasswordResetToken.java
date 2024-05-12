package com.test.fakebook.entity;

import java.util.Date;
import java.util.Objects;

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

// Entity class representing a password reset token
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "password_reset_token") // Specifies the entity name in the database
public class PasswordResetToken {
 
    // Expiration time for the token
	private static final int EXPIRATION = 60 * 24;
	
	// Method to get the expiration time
	public static int getExpiration() {
		return EXPIRATION;
	}
 
    // Primary key for the password reset token
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    // Token string
    private String token;
 
    // Many-to-one relationship with the User entity
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Column for expiry date of the token
    @Column(name = "expiry_date")
    private Date expiryDate;
    
    // Override of toString method
    @Override
    public String toString() {
        return "PasswordResetToken[id=" + id + ", token=" + token + "]";
    }
    
    // Override of hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, token); 
    }

    // Override of equals method
   @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    PasswordResetToken otherToken = (PasswordResetToken) obj; 
    return Objects.equals(id, otherToken.id) &&
           Objects.equals(token, otherToken.token); 
}

}

