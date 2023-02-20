package chatjava.gui;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chatjava.tcp.ClientConnecter;

public class GPanel extends JPanel {
    private static GPanel instance = null;
    private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
    private ArrayList<JLabel> messages = new ArrayList<>();

    private JButton endTransmission = new JButton("Chiudi trasmissione");

    private GPanel() {
        this.setLayout(layout);
        this.setBounds(40, 65, 800, 450);

        endTransmission.addActionListener(evt -> {
            ClientConnecter.getInstance().stopConnection();
            // Server.getInstance().stopConnection();
        });
        this.add(endTransmission);
    }

    public static GPanel getInstance() {
        if (instance == null) {
            instance = new GPanel();
        }
        return instance;
    }

    public void addMessage(String text) {
        if (text == null)
            return;

        messages.add(new JLabel(text));
        this.add(messages.get(messages.size() - 1));

        if (messages.size() >= 26) {
            this.removeAll();
            messages.clear();
        }

        this.repaint();
        this.revalidate();
    }
}