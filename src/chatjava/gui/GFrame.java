package chatjava.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GFrame extends JFrame {
    public GFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setContentPane(new GPanel());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("ChatGPT pezzotto");
    }
}
