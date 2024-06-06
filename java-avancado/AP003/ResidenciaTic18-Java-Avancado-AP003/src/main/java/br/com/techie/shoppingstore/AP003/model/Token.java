package br.com.techie.shoppingstore.AP003.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "token")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  private String token;

  @CreatedDate
  @Column(name = "date_creation")
  private LocalDateTime dateCreation;

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private UserSystem userSystem;

  public Token(String token, LocalDateTime dateCreation, UserSystem userSystem) {
    this.token = token;
    this.dateCreation = dateCreation;
    this.userSystem = userSystem;
  }

}
