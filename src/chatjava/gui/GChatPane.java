package chatjava.gui;

import java.awt.Color;

import javax.swing.JPanel;

public class GChatPane extends JPanel {
    private static GChatPane instance = null;

    private int prevTextY = 30;

    private GChatPane() {
        this.setBounds(0, 64, 800, 456);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public static GChatPane getInstance() {
        if (instance == null) {
            instance = new GChatPane();
        }
        return instance;
    }

    public void addMessage(String message) {
        // TODO: Prendere i messaggi dal database sql
        this.getGraphics().drawString(message, 50, prevTextY);
        prevTextY += 30;
    }
}
