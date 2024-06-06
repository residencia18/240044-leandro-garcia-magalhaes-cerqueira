package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
@EntityListeners(AuditingEntityListener.class)
public class UserSystem implements Serializable {
  private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    private String password;

    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private Role role = Role.ROLE_CLIENT;

    @CreatedDate
    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @LastModifiedDate
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    @CreatedBy
    @Column(name = "creator_by")
    private String creatorBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN")
    private boolean active;

    @Column(name = "code_verifier", length = 200)
    private String codeVerifier;

    public boolean hasNotId() {
        return id == null;
    }

    public boolean hasId() {
        return id != null;
    }

    public enum Role {
        ROLE_ADMIN, ROLE_CLIENT
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserSystem usuario = (UserSystem) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + '}';
    }

}
