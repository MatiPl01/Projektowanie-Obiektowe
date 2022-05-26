package pl.agh.edu.dp.labirynth.components.bombed;

import pl.agh.edu.dp.labirynth.components.Room;

public class BombedRoom extends Room {
    public BombedRoom(int number) {
        super(number);
    }

    @Override
    public void enter() {
        System.out.println("You entered a bombed room with number " + getRoomNumber());
    }
}
