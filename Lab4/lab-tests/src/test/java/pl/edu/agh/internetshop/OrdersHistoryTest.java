package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;
import pl.edu.agh.internetshop.filters.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrdersHistoryTest {
    @Test
    public void isOrdersHistoryListNotNull() {
        // given
        OrdersHistory ordersHistory = new OrdersHistory();

        // when
        List<Order> history = ordersHistory.getHistory();

        // then
        assertNotNull(history);
    }

    @Test
    public void getAllOrdersFromHistory() {
        // given
        List<Order> orders = Arrays.asList(
                mock(Order.class),
                mock(Order.class)
        );

        // when
        OrdersHistory ordersHistory = new OrdersHistory(orders);

        // then
        List<Order> history = ordersHistory.getHistory();
        assertEquals(orders.size(), history.size());
        assertSame(history.get(0), orders.get(0));
        assertSame(history.get(1), orders.get(1));
    }

    @Test
    public void getPastOrdersAfterAddingOrdersToHistory() {
        // given
        Order expectedOrder = mock(Order.class);

        // when
        OrdersHistory ordersHistory = new OrdersHistory();
        ordersHistory.addOrder(expectedOrder);

        // then
        List<Order> history = ordersHistory.getHistory();
        assertEquals(1, history.size());
        assertSame(expectedOrder, history.get(0));
    }

    @Test
    public void orderHistoryListIsNull() {
        // given

        // when then
        assertThrows(NullPointerException.class, () -> new OrdersHistory(null));
    }

    @Test
    public void orderInHistoryListIsNull() {
        // given
        List<Order> history = Arrays.asList(
                mock(Order.class),
                null
        );

        // when then
        assertThrows(NullPointerException.class, () -> new OrdersHistory(history));
    }

    @Test
    public void filtersSearchStrategyIsNull() {
        // given
        List<Order> orders = Arrays.asList(
                mock(Order.class),
                mock(Order.class)
        );
        OrdersHistory ordersHistory = new OrdersHistory(orders);

        // when then
        SearchStrategy strategy = null;
        assertThrows(NullPointerException.class, () -> ordersHistory.getFilteredHistory(strategy));
    }

    @Test
    public void getOrdersFilteredByProductName() {
        // given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        Product product3 = mock(Product.class);
        Product product4 = mock(Product.class);

        given(product1.getName()).willReturn("Product1");
        given(product2.getName()).willReturn("Product2");
        given(product3.getName()).willReturn("Product3");
        given(product4.getName()).willReturn("Product4");

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        Order order4 = mock(Order.class);

        given(order1.getProducts()).willReturn(Collections.singletonList(product2));
        given(order2.getProducts()).willReturn(Arrays.asList(product1, product2));
        given(order3.getProducts()).willReturn(Arrays.asList(product2, product3));
        given(order4.getProducts()).willReturn(Arrays.asList(product1, product2, product3));

        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order1, order2, order3, order4));

        SearchStrategy searchStrategy1 = new ProductNameSearchStrategy("Product1");
        SearchStrategy searchStrategy2 = new ProductNameSearchStrategy("Product2");
        SearchStrategy searchStrategy3 = new ProductNameSearchStrategy("Product3");
        SearchStrategy searchStrategy4 = new ProductNameSearchStrategy("Product4");

        // when
        List<Order> filteredOrders1 = ordersHistory.getFilteredHistory(searchStrategy1);
        List<Order> filteredOrders2 = ordersHistory.getFilteredHistory(searchStrategy2);
        List<Order> filteredOrders3 = ordersHistory.getFilteredHistory(searchStrategy3);
        List<Order> filteredOrders4 = ordersHistory.getFilteredHistory(searchStrategy4);

        // then
        assertEquals(2, filteredOrders1.size());
        assertEquals(4, filteredOrders2.size());
        assertEquals(2, filteredOrders3.size());
        assertEquals(0, filteredOrders4.size());

        filteredOrders1.forEach(order -> assertThat(order.getProducts(), hasItem(product1)));
        filteredOrders2.forEach(order -> assertThat(order.getProducts(), hasItem(product2)));
        filteredOrders3.forEach(order -> assertThat(order.getProducts(), hasItem(product3)));
    }

    @Test
    public void getOrdersFilteredByCustomerLastName() {
        // given
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);

        given(order1.getCustomerLastName()).willReturn("Customer1");
        given(order2.getCustomerLastName()).willReturn("Customer2");
        given(order3.getCustomerLastName()).willReturn("Customer1");

        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order1, order2, order3));

        SearchStrategy searchStrategy1 = new CustomerLastNameSearchStrategy("Customer1");
        SearchStrategy searchStrategy2 = new CustomerLastNameSearchStrategy("Customer2");
        SearchStrategy searchStrategy3 = new CustomerLastNameSearchStrategy("Customer3");

        // when
        List<Order> filteredOrders1 = ordersHistory.getFilteredHistory(searchStrategy1);
        List<Order> filteredOrders2 = ordersHistory.getFilteredHistory(searchStrategy2);
        List<Order> filteredOrders3 = ordersHistory.getFilteredHistory(searchStrategy3);

        // then
        assertEquals(2, filteredOrders1.size());
        assertEquals(1, filteredOrders2.size());
        assertEquals(0, filteredOrders3.size());

        filteredOrders1.forEach(order -> assertEquals(order.getCustomerLastName(), "Customer1"));
        filteredOrders2.forEach(order -> assertEquals(order.getCustomerLastName(), "Customer2"));
    }

    @Test
    public void getOrdersFilteredByTotalPrice() {
        // given
        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);

        given(order1.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(100));
        given(order2.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(200));
        given(order3.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(100));

        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order1, order2, order3));

        SearchStrategy searchStrategy1 = new TotalPriceSearchStrategy(BigDecimal.valueOf(100));
        SearchStrategy searchStrategy2 = new TotalPriceSearchStrategy(BigDecimal.valueOf(200));
        SearchStrategy searchStrategy3 = new TotalPriceSearchStrategy(BigDecimal.valueOf(300));

        // when
        List<Order> filteredOrders1 = ordersHistory.getFilteredHistory(searchStrategy1);
        List<Order> filteredOrders2 = ordersHistory.getFilteredHistory(searchStrategy2);
        List<Order> filteredOrders3 = ordersHistory.getFilteredHistory(searchStrategy3);

        // then
        assertEquals(2, filteredOrders1.size());
        assertEquals(1, filteredOrders2.size());
        assertEquals(0, filteredOrders3.size());

        filteredOrders1.forEach(order -> assertEquals(order.getPriceWithTaxes(), BigDecimal.valueOf(100)));
        filteredOrders2.forEach(order -> assertEquals(order.getPriceWithTaxes(), BigDecimal.valueOf(200)));
    }

    @Test
    public void getOrdersFilteredByCompositeFilter() {
        // given
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        Product product3 = mock(Product.class);
        Product product4 = mock(Product.class);

        given(product1.getName()).willReturn("Product1");
        given(product2.getName()).willReturn("Product2");
        given(product3.getName()).willReturn("Product3");
        given(product4.getName()).willReturn("Product4");

        Order order1 = mock(Order.class);
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);
        Order order4 = mock(Order.class);

        given(order1.getProducts()).willReturn(Collections.singletonList(product2));
        given(order2.getProducts()).willReturn(Arrays.asList(product1, product2));
        given(order3.getProducts()).willReturn(Arrays.asList(product2, product3));
        given(order4.getProducts()).willReturn(Arrays.asList(product1, product2, product3));

        given(order1.getCustomerLastName()).willReturn("Customer1");
        given(order2.getCustomerLastName()).willReturn("Customer1");
        given(order3.getCustomerLastName()).willReturn("Customer2");
        given(order4.getCustomerLastName()).willReturn("Customer3");

        given(order1.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(200));
        given(order2.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(200));
        given(order3.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(100));
        given(order4.getPriceWithTaxes()).willReturn(BigDecimal.valueOf(300));

        OrdersHistory ordersHistory = new OrdersHistory(Arrays.asList(order1, order2, order3, order4));

        SearchStrategy searchStrategy1 = new CompositeSearchStrategy(
                new ProductNameSearchStrategy("Product2"),
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(200))
        );

        SearchStrategy searchStrategy2 = new CompositeSearchStrategy(
                new ProductNameSearchStrategy("Product1"),
                new CustomerLastNameSearchStrategy("Customer3"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(300))
        );

        SearchStrategy searchStrategy3 = new CompositeSearchStrategy(
                new ProductNameSearchStrategy("Product3"),
                new CustomerLastNameSearchStrategy("Customer2"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(100))
        );

        SearchStrategy searchStrategy4 = new CompositeSearchStrategy(
                new ProductNameSearchStrategy("Product4"),
                new CustomerLastNameSearchStrategy("Customer1"),
                new TotalPriceSearchStrategy(BigDecimal.valueOf(100))
        );

        // When
        List<Order> filteredOrders1 = ordersHistory.getFilteredHistory(searchStrategy1);
        List<Order> filteredOrders2 = ordersHistory.getFilteredHistory(searchStrategy2);
        List<Order> filteredOrders3 = ordersHistory.getFilteredHistory(searchStrategy3);
        List<Order> filteredOrders4 = ordersHistory.getFilteredHistory(searchStrategy4);

        // Then
        assertEquals(2, filteredOrders1.size());
        assertEquals(1, filteredOrders2.size());
        assertEquals(1, filteredOrders3.size());
        assertEquals(0, filteredOrders4.size());

        filteredOrders1.forEach(order -> {
            assertThat(order.getProducts(), hasItem(product2));
            assertEquals("Customer1", order.getCustomerLastName());
            assertEquals(BigDecimal.valueOf(200), order.getPriceWithTaxes());
        });

        filteredOrders2.forEach(order -> {
            assertThat(order.getProducts(), hasItem(product1));
            assertEquals("Customer3", order.getCustomerLastName());
            assertEquals(BigDecimal.valueOf(300), order.getPriceWithTaxes());
        });

        filteredOrders3.forEach(order -> {
            assertThat(order.getProducts(), hasItem(product3));
            assertEquals("Customer2", order.getCustomerLastName());
            assertEquals(BigDecimal.valueOf(100), order.getPriceWithTaxes());
        });
    }
}
