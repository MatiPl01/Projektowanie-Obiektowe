package pl.edu.agh.to.lab4.search.comparators;

import pl.edu.agh.to.lab4.search.Operator;
import java.util.function.Predicate;


public class NumberComparator<N extends Number> {
    private final N value;
    public final Predicate<N> compareFunction;

    public NumberComparator(Operator operator, N value) {
        this.compareFunction = getCompareFunction(operator);
        this.value = value;
    }

    public boolean compare(N value) {
        return compareFunction.test(value);
    }

    private Predicate<N> getCompareFunction(Operator operator) {
        return switch (operator) {
            case EQ -> (value -> value.doubleValue() == this.value.doubleValue());
            case NEQ -> (value -> value.doubleValue() != this.value.doubleValue());
            case LT -> (value -> value.doubleValue() < this.value.doubleValue());
            case GT -> (value -> value.doubleValue() > this.value.doubleValue());
            case LE -> (value -> value.doubleValue() <= this.value.doubleValue());
            case GE -> (value -> value.doubleValue() >= this.value.doubleValue());
        };
    }
}
