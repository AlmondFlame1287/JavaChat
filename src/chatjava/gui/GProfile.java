package chatjava.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

public class GProfile extends JPanel {
    private static JLabel profileName = new JLabel("");

    public GProfile() {
        this.setLayout(null);
        this.setBackground(Color.BLUE);
        this.setBounds(0, 0, 800, 64);
        this.setupProfileName();
    }

    private void setupProfileName() {
        this.add(profileName);
        profileName.setBounds(20, 20, 100, 20);
    }

    public static void setProfileName(String name) {
        profileName.setText(name);
    }
}
