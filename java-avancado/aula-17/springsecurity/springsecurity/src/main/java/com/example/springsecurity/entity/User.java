package com.example.springsecurity.entity;
import com.example.springsecurity.validation.ValidPassword;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}