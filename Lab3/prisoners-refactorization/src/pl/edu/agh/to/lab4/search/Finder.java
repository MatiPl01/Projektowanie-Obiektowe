package pl.edu.agh.to.lab4.search;

import pl.edu.agh.to.lab4.data.CompositeAggregate;
import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Finder {
    private final CompositeAggregate compositeAggregate;

    public Finder(CompositeAggregate compositeAggregate) {
        this.compositeAggregate = compositeAggregate;
    }

    public void display(SearchStrategy searchStrategy) {
        Iterator<Suspect> iterator = compositeAggregate.iterator(searchStrategy);
        List<Suspect> suspectList = new ArrayList<>();

        while (iterator.hasNext() && suspectList.size() < 10) suspectList.add(iterator.next());

        System.out.println("Znalazlem " + suspectList.size() + " pasujacych podejrzanych!");
        suspectList.forEach(System.out::println);
    }
}
