package chatjava.gui;

import javax.swing.JPanel;

public class GPanel extends JPanel {

    public GPanel(GProfile profile) {
        this.setLayout(null);

        this.add(profile);
        this.add(new GChat());
    }
}