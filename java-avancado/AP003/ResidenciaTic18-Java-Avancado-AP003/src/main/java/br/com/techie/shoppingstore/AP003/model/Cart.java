package br.com.techie.shoppingstore.AP003.model;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Cart")
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalPrice;

    private int totalItems;

    private LocalDateTime purchaseDate;

    private PaymentStatusEnum status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserSystem user;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private Set<CartItem> items;
}
