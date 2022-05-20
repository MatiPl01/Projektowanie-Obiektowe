package pl.edu.agh.internetshop.filters;

import pl.edu.agh.internetshop.Order;

import java.util.Arrays;
import java.util.List;

public class CompositeSearchStrategy implements SearchStrategy {
    private final List<SearchStrategy> searchStrategies;

    public CompositeSearchStrategy(SearchStrategy ...searchStrategies) {
        this.searchStrategies = Arrays.asList(searchStrategies);
    }

    @Override
    public boolean filter(Order order) {
        return searchStrategies.stream().allMatch(searchStrategy -> searchStrategy.filter(order));
    }
}
