package pl.edu.agh.to.lab4;

import pl.edu.agh.to.lab4.data.CompositeAggregate;
import pl.edu.agh.to.lab4.data.providers.PersonDataProvider;
import pl.edu.agh.to.lab4.data.providers.PrisonersDatabase;
import pl.edu.agh.to.lab4.models.Suspect;
import pl.edu.agh.to.lab4.search.Finder;
import pl.edu.agh.to.lab4.search.Operator;
import pl.edu.agh.to.lab4.search.strategy.AgeSearchStrategy;
import pl.edu.agh.to.lab4.search.strategy.CompositeSearchStrategy;
import pl.edu.agh.to.lab4.search.strategy.NameSearchStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Application {

    public static void main(String[] args) {
        PersonDataProvider personDataProvider = new PersonDataProvider();
        PrisonersDatabase prisonersDatabase = new PrisonersDatabase();
        CompositeAggregate compositeAggregate = new CompositeAggregate(List.of(personDataProvider, prisonersDatabase));
        Finder suspects = new Finder(compositeAggregate);

        // Search by age
        System.out.println("People younger than 18");
        suspects.display(new AgeSearchStrategy(Operator.LT, 18));
        System.out.println("\nPeople who are 25 or older");
        suspects.display(new AgeSearchStrategy(Operator.GE, 25));

        // Search by name
        System.out.println("\n\nAll people with name Janusz");
        suspects.display(new NameSearchStrategy("Janusz"));

        // Search based on multiple criteria
        System.out.println("\n\nComposite filtering");
        List<Predicate<Suspect>> filters = new ArrayList<>() {{
           add(Suspect::canBeAccused);
           add(suspect -> suspect.getFirstName().startsWith("Ja"));
           add(suspect -> suspect.getAge() > 50);
        }};
        suspects.display(new CompositeSearchStrategy(filters));
    }
}
