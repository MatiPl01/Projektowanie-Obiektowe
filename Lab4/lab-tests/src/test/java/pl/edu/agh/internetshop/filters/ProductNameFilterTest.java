package pl.edu.agh.internetshop.filters;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;
import pl.edu.agh.internetshop.Product;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

public class ProductNameFilterTest {
    private Order getOrderWithMockedProducts() {
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);

        given(product1.getName()).willReturn("Product1");
        given(product2.getName()).willReturn("Product2");

        return new Order(Arrays.asList(
                product1,
                product2
        ));
    }

    @Test
    public void searchStrategyWithOrderedProductName() {
        // given
        Order order = getOrderWithMockedProducts();
        SearchStrategy searchStrategy1 = new ProductNameSearchStrategy("Product1");
        SearchStrategy searchStrategy2 = new ProductNameSearchStrategy("Product2");

        // when
        boolean matchesExisting1 = searchStrategy1.filter(order);
        boolean matchesExisting2 = searchStrategy2.filter(order);

        // then
        assertTrue(matchesExisting1);
        assertTrue(matchesExisting2);
    }

    @Test
    public void searchStrategyWithoutOrderedProductName() {
        // given
        Order order = getOrderWithMockedProducts();
        SearchStrategy searchStrategy = new ProductNameSearchStrategy("Product3");

        // when
        boolean matchesNotExisting = searchStrategy.filter(order);

        // then
        assertFalse(matchesNotExisting);
    }
}
