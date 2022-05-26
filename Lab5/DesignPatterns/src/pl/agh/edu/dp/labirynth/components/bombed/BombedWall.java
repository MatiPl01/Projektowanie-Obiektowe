package pl.agh.edu.dp.labirynth.components.bombed;

import pl.agh.edu.dp.labirynth.components.Wall;

public class BombedWall extends Wall {
    @Override
    public void enter() {
        System.out.println("You entered a bombed wall");
    }
}
