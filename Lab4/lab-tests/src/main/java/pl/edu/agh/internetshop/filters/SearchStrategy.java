package pl.edu.agh.internetshop.filters;

import pl.edu.agh.internetshop.Order;

public interface SearchStrategy {
    boolean filter(Order order);
}
