package pl.edu.agh.dronka.shop.model.items;

import pl.edu.agh.dronka.shop.model.enums.Category;
import pl.edu.agh.dronka.shop.model.enums.MusicGenre;

public class Music extends Item {
    private static final Category CATEGORY = Category.MUSIC;
    private final MusicGenre genre;
    private final boolean video;

    public Music(String name, int price, int quantity, MusicGenre genre, boolean video) {
        super(name, CATEGORY, price, quantity);
        this.genre = genre;
        this.video = video;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public boolean hasVideo() {
        return video;
    }
}
