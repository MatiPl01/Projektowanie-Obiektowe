package pl.edu.agh.internetshop.filters;

import pl.edu.agh.internetshop.Order;

import java.math.BigDecimal;

public class TotalPriceSearchStrategy implements SearchStrategy {
    private final BigDecimal totalPrice;

    public TotalPriceSearchStrategy(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean filter(Order order) {
        return order.getPriceWithTaxes().equals(totalPrice);
    }
}
