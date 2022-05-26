package pl.agh.edu.dp.test;

import org.junit.jupiter.api.Test;
import pl.agh.edu.dp.labirynth.factories.BombedMazeFactory;
import pl.agh.edu.dp.labirynth.factories.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;
import static org.junit.jupiter.api.Assertions.*;

public class MazeFactoryTest {
    @Test
    void isEnchantedMazeFactorySingleton() {
        MazeFactory mazeFactory1 = EnchantedMazeFactory.getInstance();
        MazeFactory mazeFactory2 = EnchantedMazeFactory.getInstance();

        assertSame(mazeFactory1, mazeFactory2);
    }

    @Test
    void isBombedMazeFactorySingleton() {
        MazeFactory mazeFactory1 = BombedMazeFactory.getInstance();
        MazeFactory mazeFactory2 = BombedMazeFactory.getInstance();

        assertSame(mazeFactory1, mazeFactory2);
    }

    @Test
    void areBombedAndEnchantedMazeFactoriesDifferent() {
        MazeFactory bombedMazeFactory = BombedMazeFactory.getInstance();
        MazeFactory enchantedMazeFactory = EnchantedMazeFactory.getInstance();

        assertNotSame(bombedMazeFactory, enchantedMazeFactory);
    }
}
