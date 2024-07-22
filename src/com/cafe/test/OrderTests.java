import com.cafe.entities.Order;
import com.cafe.entities.PaymentMethod;
import com.cafe.entities.OrderStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class OrderTests {

    @Test
    public void testOrderCreation() {
        UUID productId = UUID.randomUUID();
        Order order = new Order("test@example.com", productId, PaymentMethod.CREDIT_CARD, "123 Main St");

        assertEquals("test@example.com", order.getUserEmail());
        assertEquals(productId, order.getProductId());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals(PaymentMethod.CREDIT_CARD, order.getPaymentMethod());
        assertEquals("123 Main St", order.getAddress());
    }

    @Test
    public void testSetStatus() {
        Order order = new Order("test@example.com", UUID.randomUUID(), PaymentMethod.CASH, "");
        order.setStatus(OrderStatus.PREPARING);
        assertEquals(OrderStatus.PREPARING, order.getStatus());
    }

    @Test
    public void testSetAddress() {
        Order order = new Order("test@example.com", UUID.randomUUID(), PaymentMethod.DEBIT_CARD, "");
        order.setAddress("456 Elm St");
        assertEquals("456 Elm St", order.getAddress());
    }
}
