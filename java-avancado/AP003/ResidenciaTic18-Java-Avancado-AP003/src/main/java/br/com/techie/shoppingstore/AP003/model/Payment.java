package br.com.techie.shoppingstore.AP003.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Payment")
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime payday;

    private PaymentTypeEnum paymentType;

    private BigDecimal amount;
}

