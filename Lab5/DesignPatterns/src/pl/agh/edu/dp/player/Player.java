package pl.agh.edu.dp.player;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.components.Door;
import pl.agh.edu.dp.labirynth.components.MapSide;
import pl.agh.edu.dp.labirynth.components.Room;
import pl.agh.edu.dp.labirynth.components.Wall;

import java.util.Arrays;

public class Player {
    private Room currentRoom;

    public Player(Room initialRoom) {
        currentRoom = initialRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(Direction direction) {
        MapSide side = currentRoom.getSide(direction);
        side.enter();
        if (side instanceof Door door) {
            currentRoom = door.getRoom1() == currentRoom ? door.getRoom2() : door.getRoom1();
        }
    }

    public void printStatus() {
        System.out.println("Player is currently in room: " + currentRoom);
        Arrays.stream(Direction.values()).forEach(direction -> {
            if (currentRoom.getSide(direction) instanceof Wall) {
                System.out.println(direction + ": wall");
            } else if (currentRoom.getSide(direction) instanceof Door) {
                System.out.println(direction + ": door");
            } else if (currentRoom.getSide(direction) instanceof Room) {
                System.out.println(direction + ": room " + ((Room) currentRoom.getSide(direction)).getRoomNumber());
            }
        });
    }
}
