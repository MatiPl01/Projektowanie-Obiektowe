package pl.agh.edu.dp.labirynth.factories;

import pl.agh.edu.dp.labirynth.components.Room;
import pl.agh.edu.dp.labirynth.components.Wall;
import pl.agh.edu.dp.labirynth.components.Door;

public abstract class MazeFactory {
    public abstract Room createRoom(int roomNumber);

    public abstract Wall createWall();

    public Door createDoorBetween(Room room1, Room room2) {
        return new Door(room1, room2);
    }
}