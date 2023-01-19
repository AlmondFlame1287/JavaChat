package chatjava.main;

import chatjava.gui.GFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GFrame::new);
    }
}