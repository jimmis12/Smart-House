import javax.swing.*;
import java.awt.*;

public class SmartHouse implements Runnable{
    private static SmartHouseGui gui = new SmartHouseGui();
    public static void main(String[] args){

        new Thread(new SmartHouse()).start();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setSize(new Dimension(750, 498));
        //gui.setSize(new Dimension(940, 605));
        gui.setResizable(false);
        gui.setLocationRelativeTo(null);
    }

    @Override
    public void run() {
        while (true) {
            gui.repaint();
        }
    }
}
