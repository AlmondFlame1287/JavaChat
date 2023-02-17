package chatjava.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GPanel extends JPanel {
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    public GPanel() {
        this.setLayout(layout);
        this.setBounds(40, 65, 800, 450);
        test();
    }

    private void test() {
        for (int i = 0; i < 50; i++) {
            this.add(new JLabel(String.valueOf(i)));
        }
    }
}