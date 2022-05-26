package pl.agh.edu.dp.labirynth;

public enum Direction {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    private final String displayedName;

    Direction(String displayedName) {
        this.displayedName = displayedName;
    }

    @Override
    public String toString() {
        return displayedName;
    }

    public Direction getOppositeDirection() {
         Direction[] directions = Direction.values();
         return directions[(this.ordinal() + directions.length / 2) % directions.length];
    }
}
