package chatjava.gui;

import javax.swing.JPanel;

public class GPanel extends JPanel {

    public GPanel() {
        this.setLayout(null);

        this.add(new GProfile());
        this.add(new GChat());
    }
}