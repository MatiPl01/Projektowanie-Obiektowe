package pl.edu.agh.to.lab4.data.providers;

import pl.edu.agh.to.lab4.data.SuspectAggregate;
import pl.edu.agh.to.lab4.data.SuspectIterator;
import pl.edu.agh.to.lab4.models.Person;
import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.strategy.SearchStrategy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PersonDataProvider implements SuspectAggregate {

    private final Collection<Person> people = new ArrayList<>();

    public PersonDataProvider() {
        people.add(new Person("Jan", "Kowalski", 30));
        people.add(new Person("Janusz", "Krakowski", 30));
        people.add(new Person("Janusz", "Mlodociany", 10));
        people.add(new Person("Kasia", "Kosinska", 19));
        people.add(new Person("Piotr", "Zgredek", 29));
        people.add(new Person("Tomek", "Gimbus", 14));
        people.add(new Person("Janusz", "Gimbus", 15));
        people.add(new Person("Alicja", "Zaczarowana", 22));
        people.add(new Person("Janusz", "Programista", 77));
        people.add(new Person("Pawel", "Pawlowicz", 32));
        people.add(new Person("Krzysztof", "Mendel", 30));
    }

    public Collection<Person> getAllPeople() {
        return people;
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        return new SuspectIterator(people.iterator(), searchStrategy);
    }
}
