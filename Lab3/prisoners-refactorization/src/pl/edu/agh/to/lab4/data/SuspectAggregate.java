package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.Iterator;

public interface SuspectAggregate {
    Iterator<Suspect> iterator(SearchStrategy searchStrategy);
}
