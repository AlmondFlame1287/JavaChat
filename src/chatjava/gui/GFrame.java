package chatjava.gui;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import chatjava.gui.chat.GProfile;

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
        mainPanel.add(GServ.getInstance(), "Server");

        layout.show(mainPanel, "Select");
    }

    public static void changePanel(String panel, String profileName) {
        if(panel.startsWith("C")) {
            mainPanel.add(new GContainer(new GProfile(profileName)), "Chat");
        }
        layout.show(mainPanel, panel);
    }
}
