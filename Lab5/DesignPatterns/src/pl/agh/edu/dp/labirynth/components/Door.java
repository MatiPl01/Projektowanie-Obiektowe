package pl.agh.edu.dp.labirynth.components;

public class Door extends MapSide {
    private Room room1;
    private Room room2;

    public Door(Room r1, Room r2){
        this.room1 = r1;
        this.room2 = r2;
    }

    @Override
    public String toString() {
        return "Door(" + room1 + ", " + room2 + ")";
    }

    @Override
    public void enter() {
        System.out.println("You went through the door");
    }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room2 = room2;
    }
}
