package chatjava.gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GPanel extends JPanel {
    private static GPanel instance = null;
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    private ArrayList<JLabel> messages = new ArrayList<>();

    private GPanel() {
        this.setLayout(layout);
        this.setBounds(40, 65, 800, 450);
    }

    public static GPanel getInstance() {
        if(instance == null) {
            instance = new GPanel();
        }
        return instance;
    }

    public void addMessage(String text) {
        if(text == null) return;

        messages.add(new JLabel(text));
        this.add(messages.get(messages.size() - 1));

        if(messages.size() >= 26) {
            this.removeAll();
            messages.clear();
        }

        this.repaint();
        this.revalidate();
    }

    // TODO: Da eliminare nella fase di pubblicazione
    private void test() {
        for (int i = 0; i < 28; i++) {
            messages.add(new JLabel(String.valueOf(i)));
            this.add(messages.get(i));
        }
    }
}