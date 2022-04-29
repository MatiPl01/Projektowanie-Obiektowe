package pl.edu.agh.to.lab4.search.strategy;

import pl.edu.agh.to.lab4.models.Suspect;

import java.util.List;
import java.util.function.Predicate;

public class CompositeSearchStrategy implements SearchStrategy {
    private final List<Predicate<Suspect>> filters;

    public CompositeSearchStrategy(List<Predicate<Suspect>> filters) {
        this.filters = filters;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return filters.stream().allMatch(filter -> filter.test(suspect));
    }
}
