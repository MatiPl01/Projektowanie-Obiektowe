package pl.edu.agh.internetshop.filters;

import pl.edu.agh.internetshop.Order;

public class ProductNameSearchStrategy implements SearchStrategy {
    private final String productName;

    public ProductNameSearchStrategy(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean filter(Order order) {
        return order.getProducts().stream().anyMatch(o -> o.getName().equals(productName));
    }
}
