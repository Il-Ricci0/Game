import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

enum Cell {
    VOID,
    PLAYER,
    WALL
}

public class Game {
    public static Integer worldDimensionX = 50;
    public static Integer worldDimensionY = 50;
    public static Boolean end = false;
    public static Cell[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);
    // martrix[row][column]

    public static Player player = new Player("Giocatore 1", 3, 3);

    public static final Integer WINDOWWIDTH = 800;
    public static final Integer WINDOWHEIGTH = 800;

    public static final Long DELAY = 1000L;
    public static Integer tileSize = WINDOWWIDTH / worldDimensionY;

    public static void main(String[] args) throws Exception {

        //
        for (int i = 0; i < worldDimensionX; i++) {
            world[i][0] = Cell.WALL;
            world[i][worldDimensionX - 1] = Cell.WALL;
        }
        for (int i = 0; i < worldDimensionY; i++) {
            world[0][i] = Cell.WALL;
            world[worldDimensionY - 1][i] = Cell.WALL;
        }
        //

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGTH));
        panel.setLayout(new GridLayout(worldDimensionX, worldDimensionY));

        // Add a key listener to the panel
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP:
                        if (player.getRow() - 1 >= 0) {
                            if (world[player.getRow()-1][player.getColumn()] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setRow(player.getRow() - 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                        if (player.getColumn() - 1 >= 0) {
                            if (world[player.getRow()][player.getColumn()-1] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setColumn(player.getColumn() - 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                        if (player.getRow() + 1 < worldDimensionX) {
                            if (world[player.getRow()+1][player.getColumn()] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setRow(player.getRow() + 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                        if (player.getColumn() + 1 < worldDimensionY) {
                            if (world[player.getRow()][player.getColumn()+1] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setColumn(player.getColumn() + 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;
                }
            }
        });

        // Make the panel focusable so it can receive key events
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        for (int i = 0; i < worldDimensionX; i++) {
            for (int j = 0; j < worldDimensionY; j++) {
                JPanel tile = new JPanel();
                panel.add(tile);
            }
        }

        // Add the panel to a frame and display it
        JFrame window = new JFrame();
        window.setSize(WINDOWWIDTH, WINDOWHEIGTH);
        window.add(panel);
        window.setVisible(true); // Make the frame visible
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        world[player.getRow()][player.getColumn()] = Cell.PLAYER;

        Thread worldRenderer = new Thread(new WorldRenderer(panel));
        worldRenderer.start();
    }

    static Cell[][] generateEmptyWorld(Integer worldDimensionX, Integer worldDimensionY) {
        Cell[][] world = new Cell[worldDimensionX][worldDimensionY];

        for (int i = 0; i < worldDimensionX; i++) {
            for (int j = 0; j < worldDimensionY; j++) {
                world[i][j] = Cell.VOID;
            }
        }
        return world;
    }
}