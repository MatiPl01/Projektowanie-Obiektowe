package pl.edu.agh.internetshop.filters;

import pl.edu.agh.internetshop.Order;

public class CustomerLastNameSearchStrategy implements SearchStrategy {
    private final String lastName;

    public CustomerLastNameSearchStrategy(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean filter(Order order) {
        return order.getCustomerLastName().equals(lastName);
    }
}
