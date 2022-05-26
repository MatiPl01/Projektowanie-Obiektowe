package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.builders.MazeBuilder;
import pl.agh.edu.dp.labirynth.components.Room;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;
import pl.agh.edu.dp.player.Player;

import java.util.Scanner;
import java.util.Vector;

public class MazeGame {
    private static MazeGame instance;
    private final Scanner scanner = new Scanner(System.in);

    private Player player;

    private MazeGame() {}

    public static MazeGame getInstance() {
        if (instance == null) {
            instance = new MazeGame();
        }
        return instance;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void start() {
        printControlsInfo();
        run();
    }

    public void quit() {
        System.out.println("Exiting the game...");
    }

    private void run() {
        while (true) {
            player.printStatus();

            System.out.print("\nInput >>> ");
            char cmd = scanner.next().charAt(0);
            switch (cmd) {
                case 'q' | 'Q' -> {
                    quit();
                    return;
                }
                case 'w' | 'W' -> this.player.move(Direction.NORTH);
                case 'a' | 'A' -> this.player.move(Direction.WEST);
                case 's' | 'S' -> this.player.move(Direction.SOUTH);
                case 'd' | 'D' -> this.player.move(Direction.EAST);
                default -> System.out.println("Unrecognized command: " + cmd);
            }
        }
    }

    private void printControlsInfo() {
        System.out.println("Game has started. Type in 'q' to exit the game.");
        System.out.println("Type in:");
        System.out.println("  W - to move up");
        System.out.println("  A - to move left");
        System.out.println("  S - to move down");
        System.out.println("  D - to move right");
    }

    public void createMaze(MazeBuilder mazeBuilder, MazeFactory mazeFactory) {
        Vector<Room> rooms = new Vector<>();
        for (int i = 0; i < 15; i++) {
            Room room = mazeFactory.createRoom(i);
            rooms.add(room);
            mazeBuilder.addRoom(room);
        }

        try {
            mazeBuilder.setCommonWall(rooms.get(0), rooms.get(1), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(1), rooms.get(2), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(1), rooms.get(13), Direction.WEST);
            mazeBuilder.setCommonWall(rooms.get(2), rooms.get(3), Direction.EAST);
            mazeBuilder.setCommonWall(rooms.get(2), rooms.get(5), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(2), rooms.get(12), Direction.WEST);
            mazeBuilder.setCommonWall(rooms.get(3), rooms.get(4), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(4), rooms.get(5), Direction.WEST);
            mazeBuilder.setCommonWall(rooms.get(4), rooms.get(8), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(5), rooms.get(6), Direction.WEST);
            mazeBuilder.setCommonWall(rooms.get(5), rooms.get(7), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(6), rooms.get(12), Direction.NORTH);
            mazeBuilder.setCommonWall(rooms.get(7), rooms.get(8), Direction.EAST);
            mazeBuilder.setCommonWall(rooms.get(8), rooms.get(9), Direction.EAST);
            mazeBuilder.setCommonWall(rooms.get(9), rooms.get(10), Direction.SOUTH);
            mazeBuilder.setCommonWall(rooms.get(10), rooms.get(11), Direction.EAST);
            mazeBuilder.setCommonWall(rooms.get(12), rooms.get(13), Direction.NORTH);
            mazeBuilder.setCommonWall(rooms.get(13), rooms.get(14), Direction.WEST);

            mazeBuilder.addDoorBetween(rooms.get(0), rooms.get(1));
            mazeBuilder.addDoorBetween(rooms.get(1), rooms.get(2));
            mazeBuilder.addDoorBetween(rooms.get(2), rooms.get(3));
            mazeBuilder.addDoorBetween(rooms.get(3), rooms.get(4));
            mazeBuilder.addDoorBetween(rooms.get(4), rooms.get(5));
            mazeBuilder.addDoorBetween(rooms.get(5), rooms.get(6));
            mazeBuilder.addDoorBetween(rooms.get(5), rooms.get(7));
            mazeBuilder.addDoorBetween(rooms.get(7), rooms.get(8));
            mazeBuilder.addDoorBetween(rooms.get(8), rooms.get(9));
            mazeBuilder.addDoorBetween(rooms.get(9), rooms.get(10));
            mazeBuilder.addDoorBetween(rooms.get(10), rooms.get(11));
            mazeBuilder.addDoorBetween(rooms.get(1), rooms.get(13));
            mazeBuilder.addDoorBetween(rooms.get(13), rooms.get(14));
            mazeBuilder.addDoorBetween(rooms.get(13), rooms.get(12));
            mazeBuilder.addDoorBetween(rooms.get(12), rooms.get(6));
        } catch (Exception e) {
            System.out.println("Error while creating a maze");
            e.printStackTrace();
        }
    }
}
