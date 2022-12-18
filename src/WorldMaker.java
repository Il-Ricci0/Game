import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WorldMaker {
    String worldFile;

    public WorldMaker(String worldFile) {
        this.worldFile = worldFile;
    }

    public Cell[][] readfile() {
        Cell[][] world = null;
        try {
            File file = new File(worldFile);
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            Integer currentRow = 0;
            world = new Cell[lines.size()][lines.get(0).length()];
            for (String line : lines) {
                currentRow++;
                if (line.length() != lines.get(0).length()) {
                    System.out.println("World column count mismatch in row " + currentRow +"!");
                    System.exit(1);
                }
            }
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    switch (lines.get(i).charAt(j)) {
                        case '0':
                            world[i][j] = Cell.VOID;
                            break;
                        case '1':
                            world[i][j] = Cell.WALL;
                            break;
                        case '2':
                            world[i][j] = Cell.PLAYER;
                            break;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return world;
    }
}
