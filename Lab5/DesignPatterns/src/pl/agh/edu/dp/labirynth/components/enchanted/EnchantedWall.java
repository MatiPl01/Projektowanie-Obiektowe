package pl.agh.edu.dp.labirynth.components.enchanted;

import pl.agh.edu.dp.labirynth.components.Wall;

public class EnchantedWall extends Wall {
    @Override
    public void enter() {
        System.out.println("You entered an enchanted wall");
    }
}
