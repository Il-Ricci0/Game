import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Integer worldDimensionX = 10;
	    Integer worldDimensionY = 10;
        Integer playerPositionX = 0;
        Integer playerPositionY = 0;
        Integer[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);
        clrscr();
        System.out.println(printWorld(world));
        while(true){
            String action = scanner.nextLine();
            switch(action){
                case "a":
                    playerPositionY = findPlayer(world, worldDimensionX, worldDimensionY)[0];
                    playerPositionX = findPlayer(world, worldDimensionX, worldDimensionY)[1];
                    if(playerPositionY - 1 >= 0){
                        world[playerPositionX][playerPositionY] = 0;
                        playerPositionY--;
                        world[playerPositionX][playerPositionY] = 1;
                        clrscr();
                        System.out.println(printWorld(world));
                    }
                    break;
                
                case "d":
                    playerPositionY = findPlayer(world, worldDimensionX, worldDimensionY)[0];
                    playerPositionX = findPlayer(world, worldDimensionX, worldDimensionY)[1];
                    if(playerPositionY + 1 < worldDimensionY){
                        world[playerPositionX][playerPositionY] = 0;
                        playerPositionY++;
                        world[playerPositionX][playerPositionY] = 1;
                        clrscr();
                        System.out.println(printWorld(world));
                    }
                    break;
            }
        }
        //scanner.close();
    }

    static Integer[][] generateEmptyWorld(Integer worldDimensionX, Integer worldDimensionY){
        Integer[][] world = new Integer[worldDimensionX][worldDimensionY];

        for(int i = 0; i < worldDimensionX; i++){
            for(int j = 0; j < worldDimensionY; j++){
                world[i][j] = 0;
            }   
        }

        world[0][0] = 1;

        return world;
    }

    static String printWorld(Integer[][] world){
    return (Arrays.deepToString(world)
                         .replace("],","\n").replace(",","\t ")
                         .replaceAll("[\\[\\]]", " "));
    }

    static Integer[] findPlayer(Integer[][] world, Integer worldDimensionX, Integer worldDimensionY){
        Integer[] playerPosition = new Integer[2];

        for(int columnIndex = 0; columnIndex < worldDimensionX; columnIndex++){
            for(int rowIndex = 0; rowIndex < worldDimensionY; rowIndex++){
                if(world[columnIndex][rowIndex] == 1){
                    playerPosition[0] = columnIndex;
                    playerPosition[1] = rowIndex;
                }
            }   
        }

        return playerPosition;
    }

    public static void clrscr(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }
}