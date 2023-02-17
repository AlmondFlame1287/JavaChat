package chatjava.gui;

import javax.swing.JPanel;

import chatjava.gui.chat.GChat;
import chatjava.gui.chat.GProfile;

public class GPanel extends JPanel {

    public GPanel(GProfile profile) {
        this.setLayout(null);

        this.add(profile);
        this.add(new GChat());
    }
}