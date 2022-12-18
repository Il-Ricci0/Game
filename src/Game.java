import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

enum Cell {
    VOID,
    WALL,
    PLAYER
}

public class Game {
    public static final Integer WINDOWWIDTH = 800;
    public static final Integer WINDOWHEIGTH = 800;

    // martrix[row][column]

    public static Player player = new Player("Giocatore 1", 3, 3);

    public static void main(String[] args) throws Exception {

        WorldMaker worldMaker = new WorldMaker("src/maps/map.txt");
        Cell[][] world = worldMaker.readfile();

System.out.println("A "+world.length + " " + world.length);
System.out.println(world[48][0] + " " + world[48][47]);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(world.length, world[0].length));

        // Add a key listener to the panel
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W, KeyEvent.VK_UP:
                        if (player.getRow() - 1 >= 0) {
                            if (world[player.getRow() - 1][player.getColumn()] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setRow(player.getRow() - 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_A, KeyEvent.VK_LEFT:
                        if (player.getColumn() - 1 >= 0) {
                            if (world[player.getRow()][player.getColumn() - 1] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setColumn(player.getColumn() - 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_S, KeyEvent.VK_DOWN:
                        if (player.getRow() + 1 < world.length) {
                            if (world[player.getRow() + 1][player.getColumn()] != Cell.WALL) {
                                world[player.getRow()][player.getColumn()] = Cell.VOID;
                                player.setRow(player.getRow() + 1);
                                world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                            }
                        }
                        break;

                    case KeyEvent.VK_D, KeyEvent.VK_RIGHT:
                        if (player.getColumn() + 1 < world[0].length) {
                            if (world[player.getRow()][player.getColumn() + 1] != Cell.WALL) {
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

        for (int i = 0; i < world[0].length; i++) {
            for (int j = 0; j < world.length; j++) {
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

        Thread worldRenderer = new Thread(new WorldRenderer(panel, world));
        worldRenderer.start();
    }
}