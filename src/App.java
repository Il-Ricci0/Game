import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Integer worldDimensionX = 10;
	    Integer worldDimensionY = 10;
        Integer playerPositionX = 0;
        Integer playerPositionY = 0;
        Integer[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);
        System.out.println(printWorld(world));
        String action = scanner.nextLine();
        switch(action){
            case "w":
                playerPositionX = findPlayer(world, worldDimensionX, worldDimensionY)[0];
                playerPositionY = findPlayer(world, worldDimensionX, worldDimensionY)[1];
                
        }
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

        for(int i = 0; i < worldDimensionX; i++){
            for(int j = 0; j < worldDimensionY; j++){
                if(world[i][j] == 1){
                    playerPosition[0] = i;
                    playerPosition[1] = j;
                }
            }   
        }

        return playerPosition;
    }
}
