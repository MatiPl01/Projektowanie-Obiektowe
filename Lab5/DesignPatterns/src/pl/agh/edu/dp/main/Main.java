package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.builders.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.factories.EnchantedMazeFactory;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;
import pl.agh.edu.dp.player.Player;

public class Main {
    public static void main(String[] args) {
        MazeFactory mazeFactory = EnchantedMazeFactory.getInstance();
        StandardMazeBuilder mazeBuilder = new StandardMazeBuilder(mazeFactory);
        MazeGame mazeGame = MazeGame.getInstance();

        try {
            mazeGame.createMaze(mazeBuilder, mazeFactory);
            Maze maze = mazeBuilder.getCurrentMaze();
            Player player = new Player(maze.getRooms().get(0));
            mazeGame.setPlayer(player);
            mazeGame.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
