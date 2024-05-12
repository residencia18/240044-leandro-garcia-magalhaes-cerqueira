package com.test.fakebook.entity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.test.fakebook.validation.ValidPassword;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
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
@Entity(name = "users") // Specifies the entity name in the database
public class User  {

    // Primary key for the user
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Validation constraints for email
    @NotNull(message = "Email must not be null") 
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false) 
    private String email;
    

    // Validation constraints for username
    @NotNull(message = "Username must not be null") 
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long")
    @Column(unique = true, nullable = false) 
    private String username;
    
    // Custom validation for password
    @ValidPassword
    private String password;
    
    
    // Set of roles associated with the user
    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    
    // Set of password reset tokens associated with the user
    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PasswordResetToken> passwordResetTokens = new HashSet<>();
    
    
    // Override of hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, username, email); 
    }

    // Override of equals method
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(id, user.id) &&
               Objects.equals(username, user.username) &&
               Objects.equals(email, user.email); 
    }
}
