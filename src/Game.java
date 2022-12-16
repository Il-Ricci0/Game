import javax.swing.*;
import java.awt.event.*;

enum Cell {
    VOID,
    PLAYER,
    WALL
}

public class Game {
    public static Integer worldDimensionX = 40;
    public static Integer worldDimensionY = 30;
    public static Boolean end = false;
    public static Cell[][] world = generateEmptyWorld(worldDimensionX, worldDimensionY);
    public static Player player = new Player("Giocatore 1", 0, 0);
    public static final String VOID = "___";
    public static final String PLAYER = "| O |";
    // martrix[row][column]

    public static void main(String[] args) throws Exception {
        JPanel panel = new JPanel();

        // Add a key listener to the panel
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (player.getRow() - 1 >= 0) {
                            world[player.getRow()][player.getColumn()] = Cell.VOID;
                            player.setRow(player.getRow() - 1);
                            world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                        }
                        break;

                    case KeyEvent.VK_A:
                        if (player.getColumn() - 1 >= 0) {
                            world[player.getRow()][player.getColumn()] = Cell.VOID;
                            player.setColumn(player.getColumn() - 1);
                            world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                        }
                        break;

                    case KeyEvent.VK_S:
                        if (player.getRow() + 1 < worldDimensionX) {
                            world[player.getRow()][player.getColumn()] = Cell.VOID;
                            player.setRow(player.getRow() + 1);
                            world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                        }
                        break;

                    case KeyEvent.VK_D:
                        if (player.getColumn() + 1 < worldDimensionY) {
                            world[player.getRow()][player.getColumn()] = Cell.VOID;
                            player.setColumn(player.getColumn() + 1);
                            world[player.getRow()][player.getColumn()] = Cell.PLAYER;
                        }
                        break;
                }
            }
        });

        JLabel label = new JLabel();

        // Make the panel focusable so it can receive key events
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.add(label);

        // Add the panel to a frame and display it
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(750, 700); // Set the size of the frame
        frame.setVisible(true); // Make the frame visible

        world[player.getRow()][player.getColumn()] = Cell.PLAYER;

        while (true) {
            label.setText(printWorld(world));

            // Update the panel to show the new label color
            panel.revalidate();
            panel.repaint();
        }
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

    static public String printWorld(Cell[][] world) {
        String output = "<html>";
        for (int i = 0; i < worldDimensionX; i++) {
            for (int j = 0; j < worldDimensionY; j++) {
                output += world[i][j] + " ";
            }
            output += "<br>";
        }
        output += "</html>";
        return output.replaceAll("VOID", VOID).replaceAll("PLAYER", PLAYER);
    }
}