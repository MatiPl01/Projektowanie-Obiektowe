package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.builders.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.factories.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

public class Main {
    public static void main(String[] args) {
        MazeFactory mazeFactory = EnchantedMazeFactory.getInstance();
        StandardMazeBuilder mazeBuilder = new StandardMazeBuilder(mazeFactory);

        MazeGame mazeGame = new MazeGame();

        try {
            mazeGame.createMaze(mazeBuilder, mazeFactory);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Maze maze = mazeBuilder.getCurrentMaze();
        System.out.println(maze.getRoomsCount());
    }
}
