package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.builders.MazeBuilder;
import pl.agh.edu.dp.labirynth.components.Room;

public class MazeGame {
    public void createMaze(MazeBuilder builder) throws Exception {
        Room room1 = new Room(1);
        Room room2 = new Room(2);

        builder.addRoom(room1);
        builder.addRoom(room2);
        builder.setCommonWall(room1, room2, Direction.SOUTH);
        builder.addDoorBetween(room1, room2);
    }
}
