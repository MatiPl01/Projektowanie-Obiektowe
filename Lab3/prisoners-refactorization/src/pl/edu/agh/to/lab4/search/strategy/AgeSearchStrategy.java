package pl.edu.agh.to.lab4.search.strategy;

import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.Operator;
import pl.edu.agh.to.lab4.search.comparators.NumberComparator;

public class AgeSearchStrategy implements SearchStrategy {
    private final NumberComparator<Integer> comparator;

    public AgeSearchStrategy(Operator operator, int age) {
        this.comparator = new NumberComparator<>(operator, age);
    }

    @Override
    public boolean filter(Suspect suspect) {
        return comparator.compare(suspect.getAge());
    }
}
