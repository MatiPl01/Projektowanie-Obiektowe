package pl.edu.agh.to.lab4.search.strategy;

import pl.edu.agh.to.lab4.models.Suspect;

public class NameSearchStrategy implements SearchStrategy {
    private final String name;

    public NameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return suspect.getFirstName().equals(name) ||
                suspect.getLastName().equals(name);
    }
}
