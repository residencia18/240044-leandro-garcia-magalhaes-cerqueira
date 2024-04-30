package com.starbook.starbook.model;
import com.starbook.starbook.validation.ValidPassword;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Entity(name = "APP_USER")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email must not be null") // Validação no nível da aplicação
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false) // Restrições a nível de banco de dados
    private String email;

    @NotNull(message = "Username must not be null") // Validação no nível da aplicação
    @Size(min = 5, max = 15, message = "Username must be between 5 and 15 characters long")
    @Column(unique = true, nullable = false) // Restrições a nível de banco de dados
    private String username;
    
    @ValidPassword
    private String password;
    private String role;

    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinTable(name = "book_user_mapping", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<Book> books;
    
    
 
    
}
