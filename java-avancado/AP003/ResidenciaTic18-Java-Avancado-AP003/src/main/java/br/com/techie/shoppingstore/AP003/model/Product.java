package br.com.techie.shoppingstore.AP003.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private int stock;

    private String urlImage;

    private String chassis;

    private String cpu;

    private String operationalSystem;

    private String chipset;

    private String memory;

    private String slots;

    private String storage;

    private String network;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany
    @JoinColumn(name = "product_id")
    private Set<Score> scores;

}
