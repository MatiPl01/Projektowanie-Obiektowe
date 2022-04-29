package pl.edu.agh.to.lab4.search.strategy;

import pl.edu.agh.to.lab4.models.Suspect;

public interface SearchStrategy {
    boolean filter(Suspect suspect);
}
