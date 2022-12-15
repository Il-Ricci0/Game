import java.io.*;
import java.util.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App {
    static Integer worldDimensionX = 10;
    static Integer worldDimensionY = 10;
    static Boolean end = false;
    static Integer[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);
    static Player player = new Player("Giocatore 1", 0, 0);
    // martrix[row][column]

    public static void main(String[] args) throws Exception {
        Thread gameLoop = new Thread(new GameLoop());
        gameLoop.run();
    }

    

    static Integer[][] generateEmptyWorld(Integer worldDimensionX, Integer worldDimensionY) {
        Integer[][] world = new Integer[worldDimensionX][worldDimensionY];

        for (int i = 0; i < worldDimensionX; i++) {
            for (int j = 0; j < worldDimensionY; j++) {
                world[i][j] = 0;
            }
        }

        return world;
    }

    static void printWorld(Integer[][] world) {
        for (int i = 0; i < worldDimensionX; i++) {
            String row = new String();
            for (int j = 0; j < worldDimensionY; j++) {
                row += world[i][j] + " ";
            }
            System.out.println(row);
        }
    }

    static Integer[] findPlayer(Integer[][] world, Integer worldDimensionX, Integer worldDimensionY) {
        Integer[] playerPosition = new Integer[2];

        for (int columnIndex = 0; columnIndex < worldDimensionX; columnIndex++) {
            for (int rowIndex = 0; rowIndex < worldDimensionY; rowIndex++) {
                if (world[columnIndex][rowIndex] == 1) {
                    playerPosition[0] = columnIndex;
                    playerPosition[1] = rowIndex;
                }
            }
        }

        return playerPosition;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    static class GameLoop implements Runnable{
        private void gameLoop() {
            while (!end) {
                refreshScreen();
            }
        }
    
        private void refreshScreen() {
            clearConsole();
            printWorld(world);
        }

        public void run(){
            gameLoop();
        } 
    }

    public class keyEvent implements KeyListener {
        public void keyPressed(KeyEvent k) {
            switch (k.getKeyChar()) {
                case 'w':
                    world[player.getRow()][player.getColumn()] = 0;
                    player.setRow(player.getRow() - 1);
                    world[player.getRow()][player.getColumn()] = 1;
                    break;

                case 'a':
                    world[player.getRow()][player.getColumn()] = 0;
                    player.setColumn(player.getColumn() - 1);
                    world[player.getRow()][player.getColumn()] = 1;
                    break;

                case 's':
                    world[player.getRow()][player.getColumn()] = 0;
                    player.setRow(player.getRow() + 1);
                    world[player.getRow()][player.getColumn()] = 1;
                    break;

                case 'd':
                    world[player.getRow()][player.getColumn()] = 0;
                    player.setColumn(player.getColumn() + 1);
                    world[player.getRow()][player.getColumn()] = 1;
                    break;
            }
        }

        public void keyReleased(KeyEvent k) {

        }

        public void keyTyped(KeyEvent k) {

        }
    }
}