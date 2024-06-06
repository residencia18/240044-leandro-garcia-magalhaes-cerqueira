package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Scores")
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score")
    private Integer value;

    @Column(name = "user_comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserSystem user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
