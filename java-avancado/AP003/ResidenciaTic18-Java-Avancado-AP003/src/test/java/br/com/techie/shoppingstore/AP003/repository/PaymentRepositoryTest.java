package br.com.techie.shoppingstore.AP003.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;
import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.github.javafaker.Faker;
import br.com.techie.shoppingstore.AP003.model.Payment;



@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Payment payment;

    public Faker faker;

    private Payment createPayment() {
        faker = new Faker();
        Payment payment = new Payment();
        payment.setPayday(null);
        payment.setPaymentType(PaymentTypeEnum.MONEY);
        payment.setAmount(null);
        return payment;
    }


    @BeforeEach
    public void setUp() {
        faker = new Faker();
        payment = createPayment();
        entityManager.persistAndFlush(payment);
    }

    @Test
    public void testSave() {
        // Given
        Payment newPayment = createPayment();

        // When
        Payment savedPayment = paymentRepository.save(newPayment);

        // Then
        assertThat(savedPayment).isNotNull();
        assertThat(savedPayment.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindById() {
        // Given
        Payment savedPayment = paymentRepository.save(payment);

        // When
        Optional<Payment> retrievedPayment = paymentRepository.findById(savedPayment.getId());

        // Then
        assertTrue(retrievedPayment.isPresent());
        assertEquals(payment, retrievedPayment.get());
    }

    @Test
    public void testUpdate() {
        // Given
        Payment newPayment = createPayment();
        Payment savedPayment = paymentRepository.save(newPayment);

        // When
        savedPayment.setPaymentType(PaymentTypeEnum.CREDIT_CARD); // Changing the payment type
        paymentRepository.save(savedPayment);

        // Then
        Payment updatedPayment = entityManager.find(Payment.class, savedPayment.getId());
        assertEquals(PaymentTypeEnum.CREDIT_CARD, updatedPayment.getPaymentType());
    }

    @Test
    public void testDelete() {
        // Given
        Payment newPayment = createPayment();
        Payment savedPayment = paymentRepository.save(newPayment);

        // When
        paymentRepository.delete(savedPayment);

        // Then
        assertFalse(paymentRepository.existsById(savedPayment.getId()));
    }
}


