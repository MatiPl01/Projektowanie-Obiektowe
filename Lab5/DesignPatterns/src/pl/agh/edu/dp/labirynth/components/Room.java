package pl.agh.edu.dp.labirynth.components;

import pl.agh.edu.dp.labirynth.Direction;

import java.util.EnumMap;
import java.util.Map;

public abstract class Room extends MapSide {
    private final int roomNumber;
    private final Map<Direction, MapSide> sides;

    public Room(int number) {
        this.sides = new EnumMap<>(Direction.class);
        this.roomNumber = number;
    }

    @Override
    public String toString() {
        return String.valueOf(roomNumber);
    }

    public MapSide getSide(Direction direction){
        return this.sides.get(direction);
    }

    public void setSide(Direction direction, MapSide ms){
        this.sides.put(direction, ms);
    }

    public int getRoomNumber(){
        return this.roomNumber;
    }
}
