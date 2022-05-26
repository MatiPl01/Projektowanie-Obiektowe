package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.builders.MazeBuilder;
import pl.agh.edu.dp.labirynth.components.Room;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

public class MazeGame {
    public void createMaze(MazeBuilder builder, MazeFactory mazeFactory) throws Exception {
        Room room1 = mazeFactory.createRoom(1);
        Room room2 = mazeFactory.createRoom(2);

        builder.addRoom(room1);
        builder.addRoom(room2);
        builder.setCommonWall(room1, room2, Direction.SOUTH);
        builder.addDoorBetween(room1, room2);
    }
}
