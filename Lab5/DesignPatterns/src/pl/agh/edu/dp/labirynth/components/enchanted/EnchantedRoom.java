package pl.agh.edu.dp.labirynth.components.enchanted;

import pl.agh.edu.dp.labirynth.components.Room;

public class EnchantedRoom extends Room {
    public EnchantedRoom(int number) {
        super(number);
    }

    @Override
    public void enter() {
        System.out.println("You entered an enchanted room with number " + getRoomNumber());
    }
}
