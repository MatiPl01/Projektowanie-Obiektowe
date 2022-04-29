package pl.edu.agh.to.lab4.data.providers;

import pl.edu.agh.to.lab4.data.SuspectAggregate;
import pl.edu.agh.to.lab4.data.SuspectIterator;
import pl.edu.agh.to.lab4.models.Person;
import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PrisonerDataProvider implements SuspectAggregate {

    private final Collection<Person> prisoners = new ArrayList<>();

    public PrisonerDataProvider() {
        prisoners.add(new Person("Jan", "Kowalski", 30));
        prisoners.add(new Person("Janusz", "Krakowski", 30));
        prisoners.add(new Person("Janusz", "Mlodociany", 10));
        prisoners.add(new Person("Kasia", "Kosinska", 19));
        prisoners.add(new Person("Piotr", "Zgredek", 29));
        prisoners.add(new Person("Tomek", "Gimbus", 14));
        prisoners.add(new Person("Janusz", "Gimbus", 15));
        prisoners.add(new Person("Alicja", "Zaczarowana", 22));
        prisoners.add(new Person("Janusz", "Programista", 77));
        prisoners.add(new Person("Pawel", "Pawlowicz", 32));
        prisoners.add(new Person("Krzysztof", "Mendel", 30));
    }

    public Collection<Person> getAllPrisoners() {
        return prisoners;
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        return new SuspectIterator(prisoners.iterator(), searchStrategy);
    }
}
