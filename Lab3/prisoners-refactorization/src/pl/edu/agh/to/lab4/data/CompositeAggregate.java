package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CompositeAggregate implements SuspectAggregate {
    private final List<SuspectAggregate> aggregateList;

    public CompositeAggregate(List<SuspectAggregate> aggregateList) {
        this.aggregateList = aggregateList;
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        Collection<Suspect> suspects = new ArrayList<>();
        aggregateList.forEach(suspectAggregate -> {
            Iterator<Suspect> iterator = suspectAggregate.iterator(searchStrategy);
            while (iterator.hasNext()) suspects.add(iterator.next());
        });
        return suspects.iterator();
    }
}
