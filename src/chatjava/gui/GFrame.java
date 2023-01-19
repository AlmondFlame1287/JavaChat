package chatjava.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GFrame extends JFrame {
    private static CardLayout layout = new CardLayout();
    private static JPanel mainPanel = new JPanel(layout);

    public GFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setResizable(false);
        this.setContentPane(mainPanel);

        this.setupMainPanel();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setTitle("ChatGPT pezzotto");
    }

    private void setupMainPanel() {
        mainPanel.add(new GSelect(), "Select");
        mainPanel.add(new GPanel(), "Chat");

        layout.show(mainPanel, "Select");
    }

    public static void changePanel(String panel) {
        layout.show(mainPanel, panel);
    }
}
