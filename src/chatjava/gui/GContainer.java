package chatjava.gui;

import javax.swing.JScrollPane;

import chatjava.gui.chat.GChat;
import chatjava.gui.chat.GProfile;

public class GContainer extends JScrollPane {
    public GContainer(GProfile profile) {
        this.setLayout(null);

        this.add(profile);
        this.add(new GPanel());
        this.add(new GChat());
    }
}