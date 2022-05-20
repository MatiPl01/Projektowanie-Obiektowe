package pl.edu.agh.internetshop.filters;


import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Customer;
import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

public class CompositeFilterTest {
    private Order getMockedOrder() {
        Customer customer = mock(Customer.class);
        given(customer.getLastName()).willReturn("Customer1");

        Product product1 = new Product("Product1", BigDecimal.valueOf(1));
        Product product2 = new Product("Product2", BigDecimal.valueOf(2));

        return new Order(Arrays.asList(product1, product2), customer);
    }

    @Test
    public void allParametersTheSameAsInTheOrder() {
        // given
        Order order = getMockedOrder();
        SearchStrategy compositeSearchStrategy1 = new CompositeSearchStrategy(
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(3.69)), // Price with tax added
                new ProductNameSearchStrategy("Product1")
        );

        SearchStrategy compositeSearchStrategy2 = new CompositeSearchStrategy(
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(3.69)), // Price with tax added
                new ProductNameSearchStrategy("Product2")
        );

        // when
        boolean matchesWithProduct1 = compositeSearchStrategy1.filter(order);
        boolean matchesWithProduct2 = compositeSearchStrategy2.filter(order);

        // then
        assertTrue(matchesWithProduct1);
        assertTrue(matchesWithProduct2);
    }

    @Test
    public void allParametersTheSameExceptProductName() {
        // given
        Order order = getMockedOrder();
        SearchStrategy compositeSearchStrategy = new CompositeSearchStrategy(
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(3.69)), // Price with tax added
                new ProductNameSearchStrategy("Product3")
        );

        // when
        boolean matches = compositeSearchStrategy.filter(order);

        // then
        assertFalse(matches);
    }

    @Test
    public void allParametersTheSameExceptTotalPrice() {
        // given
        Order order = getMockedOrder();
        SearchStrategy compositeSearchStrategy = new CompositeSearchStrategy(
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(3)), // Price without tax added (incorrect)
                new ProductNameSearchStrategy("Product1")
        );

        // when
        boolean matches = compositeSearchStrategy.filter(order);

        // then
        assertFalse(matches);
    }

    @Test
    public void allParametersTheSameExceptLastName() {
        // given
        Order order = getMockedOrder();
        SearchStrategy compositeSearchStrategy = new CompositeSearchStrategy(
                new CustomerLastNameSearchStrategy("Customer2"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(3.69)), // Price with tax added
                new ProductNameSearchStrategy("Product1")
        );

        // when
        boolean matches = compositeSearchStrategy.filter(order);

        // then
        assertFalse(matches);
    }
}
