import javax.swing.*;

class WorldRenderer implements Runnable{
    private JLabel label;
    private JPanel panel;
    public  WorldRenderer(JLabel label, JPanel panel){
        this.label = label;
        this.panel = panel;
    }
    public String prerenderWorld() {
        String output = "<html>";
        for (int i = 0; i < Game.worldDimensionX; i++) {
            for (int j = 0; j < Game.worldDimensionY; j++) {
                output += Game.world[i][j] + " ";
            }
            output += "<br>";
        }
        output += "</html>";
        return output.replaceAll("VOID", Game.VOID).replaceAll("PLAYER", Game.PLAYER);
    }
    public void renderWorld() {
        label.setText(prerenderWorld());
        panel.revalidate();
        panel.repaint();
    }

    public void run(){
        while(true){
            renderWorld();
        }
        
    }
}
