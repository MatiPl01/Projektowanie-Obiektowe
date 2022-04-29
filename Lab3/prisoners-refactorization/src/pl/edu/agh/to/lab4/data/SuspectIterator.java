package pl.edu.agh.to.lab4.data;

import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SuspectIterator implements Iterator<Suspect> {
    private final Iterator<? extends Suspect> iterator;
    private final SearchStrategy searchStrategy;
    private Suspect currentSuspect;

    public SuspectIterator(Iterator<? extends Suspect> iterator, SearchStrategy searchStrategy) {
        this.iterator = iterator;
        this.searchStrategy = searchStrategy;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            currentSuspect = iterator.next();
            if (searchStrategy.filter(currentSuspect)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Suspect next() {
        if (currentSuspect != null) return currentSuspect;
        throw new NoSuchElementException("There are no more suspects");
    }
}
