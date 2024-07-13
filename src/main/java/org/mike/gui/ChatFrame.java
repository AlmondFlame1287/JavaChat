package org.mike.gui;

import javax.swing.*;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setContentPane(new LoginPane(this));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
