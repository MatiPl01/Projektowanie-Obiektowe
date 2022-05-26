package pl.agh.edu.dp.labirynth.builders;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.components.Room;

public class CountingMazeBuilder implements MazeBuilder {
    private int roomsCount = 0;
    private int wallsCount = 0;
    private int doorsCount = 0;

    @Override
    public void addRoom(Room room) {
        roomsCount++;
    }

    @Override
    public void setCommonWall(Room room1, Room room2, Direction room1Direction) {
        wallsCount++;
    }

    @Override
    public void addDoorBetween(Room room1, Room room2) {
        doorsCount++;
    }

    public int getRoomsCount() {
        return roomsCount;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public int getCounts() {
        return roomsCount + wallsCount + doorsCount;
    }
}
