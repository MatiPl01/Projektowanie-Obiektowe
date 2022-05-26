package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.builders.StandardMazeBuilder;

public class Main {
    public static void main(String[] args) {
        StandardMazeBuilder mazeBuilder = new StandardMazeBuilder();

        MazeGame mazeGame = new MazeGame();

        try {
            mazeGame.createMaze(mazeBuilder);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Maze maze = mazeBuilder.getCurrentMaze();
        System.out.println(maze.getRoomsCount());
    }
}
