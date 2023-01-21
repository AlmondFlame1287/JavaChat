package chatjava.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GPanel extends JPanel {
    private JScrollPane scrollPane;

    public GPanel() {
        this.setLayout(null);
        this.setupScrollPane();
        this.add(new GProfile());
        this.add(scrollPane);
        this.add(new GChat());
    }

    private void setupScrollPane() {
        scrollPane = new JScrollPane(GChatPane.getInstance());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBounds(0, 64, 800, 456);
    }

}