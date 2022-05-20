package pl.edu.agh.internetshop;

import pl.edu.agh.internetshop.filters.SearchStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class OrdersHistory {
    private final List<Order> history;

    public OrdersHistory() {
        this.history = new ArrayList<>();
    }

    public OrdersHistory(Collection<Order> history) {
        Objects.requireNonNull(history, "Orders history cannot be null");
        this.history = new ArrayList<>(history);
        this.history.forEach(order -> Objects.requireNonNull(order, "Order in history cannot be null"));
    }

    public void addOrder(Order order) {
        this.history.add(Objects.requireNonNull(order, "Order cannot be null"));
    }

    public List<Order> getHistory() {
        return history;
    }

    public List<Order> getFilteredHistory(SearchStrategy searchStrategy) {
        return history.stream().filter(searchStrategy::filter).collect(Collectors.toList());
    }
}
