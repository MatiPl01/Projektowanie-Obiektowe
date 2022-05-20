package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        // TODO - REMOVE ME
        BigDecimal expectedProductPrice = BigDecimal.valueOf(1000);
        Product product = new Product("apple", expectedProductPrice);
        Order order = new Order(Collections.singletonList(product));
        System.out.println("WORKS: " + order.getPrice());
        System.out.println(order.getProducts().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println(product.getPrice());
        System.out.println(product.getDiscount().getValue());
    }
}
