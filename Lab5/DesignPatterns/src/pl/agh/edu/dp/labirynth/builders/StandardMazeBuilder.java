package pl.agh.edu.dp.labirynth.builders;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.Maze;
import pl.agh.edu.dp.labirynth.components.Door;
import pl.agh.edu.dp.labirynth.components.MapSide;
import pl.agh.edu.dp.labirynth.components.Room;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

import java.util.Arrays;

public class StandardMazeBuilder implements MazeBuilder {
    private final MazeFactory mazeFactory;
    private final Maze currentMaze = new Maze();

    public Maze getCurrentMaze() {
        return currentMaze;
    }

    public StandardMazeBuilder(MazeFactory mazeFactory) {
        this.mazeFactory = mazeFactory;
    }

    @Override
    public void addRoom(Room room) {
        Arrays.stream(Direction.values())
                .forEach(direction -> room.setSide(direction, mazeFactory.createWall()));
        currentMaze.addRoom(room);
    }

    @Override
    public void setCommonWall(Room room1, Room room2, Direction room1Direction) throws Exception {
        // Get the wall of the room1 which has the specified wallDirection
        MapSide side = room1.getSide(room1Direction);
        if (side == null) throw new Exception("Cannot get the " + room1Direction + " side of the room " + room1);
        // Set the side of the first room as the opposite direction side of the second room
        // (in other words, set the side as a common wall between the rooms specified)
        room2.setSide(room1Direction.getOppositeDirection(), side);
    }

    @Override
    public void addDoorBetween(Room room1, Room room2) throws Exception {
        // Find the common wall direction between the rooms specified
        Direction room1Direction = commonWallDirectionRelativeToRoom1(room1, room2);
        if (room1Direction == null) {
            throw new Exception("Room " + room1 + " and room " + room2 + " don't have a common wall");
        }
        // Create the door on the common wall between the rooms
        Door door = mazeFactory.createDoorBetween(room1, room2);
        room1.setSide(room1Direction, door);
        room2.setSide(room1Direction.getOppositeDirection(), door);
    }

    private Direction commonWallDirectionRelativeToRoom1(Room room1, Room room2) {
        for (Direction direction: Direction.values()) {
            if (room1.getSide(direction) == room2.getSide(direction.getOppositeDirection())) {
                return direction;
            }
        }
        return null;
    }
}
