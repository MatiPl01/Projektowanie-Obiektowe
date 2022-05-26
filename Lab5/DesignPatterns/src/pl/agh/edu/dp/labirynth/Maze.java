package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.components.Room;

import java.util.Vector;

public class Maze {
    private Vector<Room> rooms;

    public Maze() {
        this.rooms = new Vector<>();
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public Vector<Room> getRooms(){
        return rooms;
    }

    public void setRooms(Vector<Room> rooms) {
        this.rooms = rooms;
    }

    public int getRoomsCount() {
        return rooms.size();
    }
}
