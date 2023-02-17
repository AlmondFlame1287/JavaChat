package chatjava.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;

public class GProfile extends JPanel {
    public GProfile(String name) {
        this.setLayout(null);
        this.setBounds(0, 0, 800, 64);
        this.setBackground(Color.CYAN);
        this.printName(name);
    }

    private void printName(String name) {
        JLabel lbl = new JLabel(name);
        lbl.setBounds(100, 20, 150, 20);
        this.add(lbl);
        this.revalidate();
    }

    private void printPicture() {

    }
}
