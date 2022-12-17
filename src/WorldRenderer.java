import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class WorldRenderer implements Runnable {
    private JPanel panel;

    public WorldRenderer(JPanel panel) {
        this.panel = panel;
    }

    public void renderWorld() {
        java.util.List<Cell> listifiedWorldPrerenderedMatrix = new ArrayList<Cell>();
        for (int i = 0; i < Game.worldDimensionX; i++) {
            for (int j = 0; j < Game.worldDimensionY; j++) {
                listifiedWorldPrerenderedMatrix.add(Game.world[i][j]);
            }
        }
        Component[] panelComponents = panel.getComponents();
        for (int i = 0; i < listifiedWorldPrerenderedMatrix.size(); i++) {
            switch (listifiedWorldPrerenderedMatrix.get(i)) {
                case VOID:
                    panelComponents[i].setBackground(Color.BLACK);
                    break;
                case WALL:
                    panelComponents[i].setBackground(Color.ORANGE);
                    break;
                case PLAYER:
                    panelComponents[i].setBackground(Color.BLUE);
                    break;
            }
        }
    }

    public void run() {
        while (true) {
            renderWorld();
        }
    }
}
