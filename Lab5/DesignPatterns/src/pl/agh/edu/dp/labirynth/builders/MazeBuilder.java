package pl.agh.edu.dp.labirynth.builders;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.components.Room;

public interface MazeBuilder {
    void addRoom(Room room);
    void setCommonWall(Room room1, Room room2, Direction room1Direction) throws Exception;
    void addDoorBetween(Room room1, Room room2) throws Exception;
}
