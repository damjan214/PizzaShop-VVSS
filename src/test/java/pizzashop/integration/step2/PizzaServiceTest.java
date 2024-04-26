package pizzashop.integration.step2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    PaymentRepository payRepo;

    PizzaService service;

    @BeforeEach
    void setUp() {
        payRepo=new PaymentRepository();
        service=new PizzaService(null,payRepo);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    void getPayments() {
        List<Payment> result=service.getPayments();
        assertEquals(0,result.size());
    }

    @Test
    void addPayment() {
        Payment payment= mock(Payment.class);
        when(payment.getTableNumber()).thenReturn(3);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.getAmount()).thenReturn(3.0);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        List<Payment> result=service.getPayments();
        assertEquals(1,result.size());
        assertEquals(payment,result.get(0));
    }
}