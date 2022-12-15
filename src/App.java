import java.io.*;
import java.util.*;

//francesi polacchiu

public class App {
    static Integer worldDimensionX = 10;
    static Integer worldDimensionY = 10;
    static Boolean end = false;
    static Integer[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);

    public static void main(String[] args) throws Exception {
        gameLoop();
    }

    static void gameLoop() {
        Player player = new Player("Giocatore 1", 0, 0);
        while (!end) {
            getInput(player);
            refreshScreen();
        }
    }

    static void refreshScreen() {
        clearConsole();
        System.out.println(printWorld(world));
    }

    static void getInput(Player player) {
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();
        switch (action) {
            case "w":
            
                break;
            
            case "a":

            break;

            case "s":

                break;

            case "d":

                break;

            // default:
            //     end = true;
            //     clearConsole();
            //     System.out.println("FINE DEL GIOCO!");
            //     break;
        }
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

    static String printWorld(Integer[][] world) {
        return (Arrays.deepToString(world)
                .replace("],", "\n").replace(",", "\t ")
                .replaceAll("[\\[\\]]", " "));
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
}