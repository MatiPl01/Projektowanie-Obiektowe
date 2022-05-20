package pl.edu.agh.internetshop.filters;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.Order;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.BDDMockito.given;

public class TotalPriceFilterTest {
    private static final BigDecimal ORDER_PRICE = BigDecimal.valueOf(1);

    private Order getMockedOrder() {
        Order order = mock(Order.class);
        given(order.getPriceWithTaxes()).willReturn(ORDER_PRICE);
        return order;
    }

    @Test
    public void searchStrategyWithDifferentTotalPrice() {
        // given
        Order order = getMockedOrder();
        SearchStrategy searchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(2));

        // when
        boolean matches = searchStrategy.filter(order);

        // then
        assertFalse(matches);
    }

    @Test
    public void searchStrategyWithTheSameTotalPrice() {
        // given
        Order order = getMockedOrder();
        SearchStrategy searchStrategy = new TotalPriceSearchStrategy(BigDecimal.valueOf(1));

        // when
        boolean matches = searchStrategy.filter(order);

        // then
        assertTrue(matches);
    }
}
