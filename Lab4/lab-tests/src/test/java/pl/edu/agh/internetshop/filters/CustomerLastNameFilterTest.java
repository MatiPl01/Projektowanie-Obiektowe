package pl.edu.agh.internetshop.filters;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Customer;
import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

public class CustomerLastNameFilterTest {
    private Order getOrderWithMockedCustomer() {
        Customer customer = mock(Customer.class);
        Product product = mock(Product.class);

        given(customer.getLastName()).willReturn("Customer1");

        return new Order(Collections.singletonList(product), customer);
    }

    @Test
    public void searchStrategyWithExistingOrderCustomer() {
        // given
        Order order = getOrderWithMockedCustomer();
        SearchStrategy searchStrategy = new CustomerLastNameSearchStrategy("Customer1");

        // when
        boolean matchesExisting = searchStrategy.filter(order);

        // then
        assertTrue(matchesExisting);
    }

    @Test
    public void searchStrategyWithoutExistingOrderCustomer() {
        // given
        Order order = getOrderWithMockedCustomer();
        SearchStrategy searchStrategy = new CustomerLastNameSearchStrategy("Customer2");

        // when
        boolean matchesNotExisting = searchStrategy.filter(order);

        // then
        assertFalse(matchesNotExisting);
    }
}
