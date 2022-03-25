package pl.edu.agh.dronka.shop.model.enums;

public enum MusicGenre {
    POP("pop"),
    RAP("rap"),
    HIPHOP("hip-hop"),
    JAZZ("jazz");

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    MusicGenre(String displayName) {
        this.displayName = displayName;
    }
}
